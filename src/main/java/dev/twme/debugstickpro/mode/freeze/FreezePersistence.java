package dev.twme.debugstickpro.mode.freeze;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.scheduler.PlatformScheduler;
import dev.twme.debugstickpro.utils.Log;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Records every barrier anchor on disk, so blocks frozen while the server stops
 * unexpectedly are restored the next time the plugin starts.
 */
public final class FreezePersistence {
    private static final String FILE_NAME = "frozen-blocks.yml";

    /**
     * Anchors from a previous run whose world is not loaded yet.
     */
    private static final Map<FreezeLocation, Entry> pending = new ConcurrentHashMap<>();

    /**
     * Restored anchors stay recorded until their world has been saved twice, because the restore
     * only reaches the region files with a later save; a crash before that brings the barrier back.
     */
    private static final Map<FreezeLocation, Entry> released = new ConcurrentHashMap<>();
    private static final Map<FreezeLocation, Entry> releasedAndSaved = new ConcurrentHashMap<>();

    private static Path file;

    private FreezePersistence() {
    }

    /**
     * Loads anchors left by a previous run and restores those in loaded worlds.
     */
    public static synchronized void initialize(Plugin plugin) {
        file = plugin.getDataFolder().toPath().resolve(FILE_NAME);
        pending.clear();
        released.clear();
        releasedAndSaved.clear();
        if (!Files.exists(file)) {
            return;
        }

        YamlConfiguration yaml = new YamlConfiguration();
        try {
            yaml.loadFromString(Files.readString(file, StandardCharsets.UTF_8));
        } catch (IOException | InvalidConfigurationException ex) {
            Log.warning("Could not read " + FILE_NAME + ": " + ex.getMessage());
            return;
        }

        for (Map<?, ?> rawEntry : yaml.getMapList("blocks")) {
            Entry entry = Entry.deserialize(rawEntry);
            if (entry != null) {
                pending.put(entry.location(), entry);
            }
        }

        for (World world : Bukkit.getWorlds()) {
            recoverWorld(world);
        }
        save(List.of());
    }

    /**
     * Restores anchors of a world that was not loaded when the plugin started.
     */
    public static void recoverWorld(World world) {
        UUID worldUUID = world.getUID();
        for (Entry entry : new ArrayList<>(pending.values())) {
            if (!entry.location().worldUUID().equals(worldUUID)) {
                continue;
            }

            pending.remove(entry.location());
            Block block = world.getBlockAt(entry.location().x(), entry.location().y(), entry.location().z());
            PlatformScheduler.runAtLocation(DebugStickPro.getInstance(), block.getLocation(), () -> restore(block, entry));
        }
    }

    static void release(FreezeBlockData frozenData) {
        Entry entry = Entry.of(frozenData);
        releasedAndSaved.remove(entry.location());
        released.put(entry.location(), entry);
    }

    /**
     * Forgets restored anchors of a world once a save that started after their restore has run.
     */
    public static void onWorldSaved(World world) {
        UUID worldUUID = world.getUID();
        boolean changed = releasedAndSaved.keySet().removeIf(location -> location.worldUUID().equals(worldUUID));
        for (Entry entry : new ArrayList<>(released.values())) {
            if (entry.location().worldUUID().equals(worldUUID) && released.remove(entry.location(), entry)) {
                releasedAndSaved.put(entry.location(), entry);
            }
        }
        if (changed) {
            save(FreezeBlockManager.getFrozenBlocks());
        }
    }

    /**
     * Writes the current anchors together with restored anchors not saved yet and anchors still
     * waiting for their world.
     */
    static synchronized void save(Collection<FreezeBlockData> frozenBlocks) {
        if (file == null) {
            return;
        }

        List<Map<String, Object>> entries = new ArrayList<>();
        Set<FreezeLocation> written = new HashSet<>();
        for (FreezeBlockData frozenData : frozenBlocks) {
            if (written.add(frozenData.getLocation())) {
                entries.add(Entry.of(frozenData).serialize());
            }
        }
        for (Map<FreezeLocation, Entry> map : List.of(released, releasedAndSaved, pending)) {
            for (Entry entry : map.values()) {
                if (written.add(entry.location())) {
                    entries.add(entry.serialize());
                }
            }
        }

        try {
            if (entries.isEmpty()) {
                Files.deleteIfExists(file);
                return;
            }

            YamlConfiguration yaml = new YamlConfiguration();
            yaml.set("blocks", entries);
            Files.createDirectories(file.getParent());
            Path temp = file.resolveSibling(FILE_NAME + ".tmp");
            Files.writeString(temp, yaml.saveToString(), StandardCharsets.UTF_8);
            try {
                Files.move(temp, file, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
            } catch (AtomicMoveNotSupportedException ex) {
                Files.move(temp, file, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException ex) {
            Log.warning("Could not save " + FILE_NAME + ": " + ex.getMessage());
        }
    }

    /**
     * Copies container contents, since replacing a block with the anchor removes its block entity.
     */
    static Map<Integer, ItemStack> captureContents(BlockState state) {
        if (!(state instanceof Container container)) {
            return Map.of();
        }

        Map<Integer, ItemStack> contents = new HashMap<>();
        ItemStack[] items = container.getSnapshotInventory().getContents();
        for (int slot = 0; slot < items.length; slot++) {
            if (items[slot] != null && !items[slot].getType().isAir()) {
                contents.put(slot, items[slot].clone());
            }
        }
        return contents;
    }

    private static void restore(Block block, Entry entry) {
        // Something else replaced the anchor after the crash; keep it.
        if (block.getType() != Material.BARRIER) {
            return;
        }

        try {
            block.setBlockData(Bukkit.createBlockData(entry.blockData()), false);
        } catch (IllegalArgumentException ex) {
            Log.warning("Could not restore frozen block at " + entry.location() + ": " + ex.getMessage());
            return;
        }
        Log.warning("Restored a block left frozen by an unexpected shutdown at " + entry.location());

        if (!entry.contents().isEmpty() && block.getState() instanceof Container container) {
            Inventory inventory = container.getSnapshotInventory();
            entry.contents().forEach((slot, item) -> {
                if (slot < inventory.getSize()) {
                    inventory.setItem(slot, item);
                }
            });
            container.update(true, false);
        }
    }

    private record Entry(FreezeLocation location, String blockData, Map<Integer, ItemStack> contents) {

        static Entry of(FreezeBlockData frozenData) {
            return new Entry(frozenData.getLocation(), frozenData.getBlockString(), frozenData.getContents());
        }

        Map<String, Object> serialize() {
            Map<String, Object> map = new HashMap<>();
            map.put("world", location.worldUUID().toString());
            map.put("x", location.x());
            map.put("y", location.y());
            map.put("z", location.z());
            map.put("data", blockData);
            if (!contents.isEmpty()) {
                Map<String, Object> items = new HashMap<>();
                contents.forEach((slot, item) -> items.put(String.valueOf(slot), item));
                map.put("contents", items);
            }
            return map;
        }

        static Entry deserialize(Map<?, ?> map) {
            try {
                FreezeLocation location = new FreezeLocation(
                        UUID.fromString(String.valueOf(map.get("world"))),
                        ((Number) map.get("x")).intValue(),
                        ((Number) map.get("y")).intValue(),
                        ((Number) map.get("z")).intValue()
                );
                String blockData = String.valueOf(map.get("data"));

                Map<Integer, ItemStack> contents = new HashMap<>();
                Object rawContents = map.get("contents");
                Map<?, ?> items = rawContents instanceof ConfigurationSection section
                        ? section.getValues(false)
                        : rawContents instanceof Map<?, ?> rawMap ? rawMap : Map.of();
                items.forEach((slot, item) -> {
                    if (item instanceof ItemStack itemStack) {
                        contents.put(Integer.parseInt(String.valueOf(slot)), itemStack);
                    }
                });
                return new Entry(location, blockData, contents);
            } catch (RuntimeException ex) {
                Log.warning("Skipping invalid entry in " + FILE_NAME + ": " + map);
                return null;
            }
        }
    }
}
