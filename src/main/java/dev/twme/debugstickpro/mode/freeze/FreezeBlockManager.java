package dev.twme.debugstickpro.mode.freeze;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.protocol.entity.type.EntityTypes;
import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.protocol.item.type.ItemTypes;
import com.github.retrooper.packetevents.util.Vector3f;
import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.scheduler.PlatformScheduler;
import io.github.retrooper.packetevents.util.SpigotConversionUtil;
import io.github.twme.virtualentities.VirtualEntity;
import io.github.twme.virtualentities.VirtualViewer;
import io.github.twme.virtualentities.metadata.EntityMetadataFlags;
import io.github.twme.virtualentities.metadata.GeneratedEntityMetadataKeys;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.FaceAttachable;
import org.bukkit.block.data.Rail;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Frozen blocks are anchored as barriers on the server, so neighbor shape updates
 * cannot change them. Clients keep seeing the frozen block state through fake
 * block packets (see {@link FreezePacketLayer}) and a glowing outline entity.
 */
public class FreezeBlockManager {
    private static final BlockFace[] ADJACENT_FACES = {
            BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN
    };

    private static final Map<FreezeLocation, FreezeBlockData> frozenBlocks = new ConcurrentHashMap<>();
    private static final Map<UUID, Set<FreezeLocation>> playerFrozenBlocks = new ConcurrentHashMap<>();
    private static final Map<FreezeLocation.FreezeChunk, Set<FreezeLocation>> frozenChunks = new ConcurrentHashMap<>();
    private static final Map<FreezeLocation, String> protectedDependentBlocks = new ConcurrentHashMap<>();

    public static void freezeBlock(UUID playerUUID, Block block) {
        FreezeLocation location = FreezeLocation.of(block);
        if (frozenBlocks.containsKey(location)) {
            return;
        }

        int clientStateId = SpigotConversionUtil.fromBukkitBlockData(block.getBlockData()).getGlobalId();
        FreezeBlockData frozenData = new FreezeBlockData(playerUUID, block, clientStateId, createOutline(block));

        // Register before the anchor is placed so the barrier update is rewritten to the frozen state.
        frozenBlocks.put(location, frozenData);
        addToIndex(playerFrozenBlocks, playerUUID, location);
        addToIndex(frozenChunks, location.chunk(), location);
        protectedDependentBlocks.remove(location);

        block.setBlockData(Material.BARRIER.createBlockData(), false);

        for (Player player : block.getWorld().getPlayers()) {
            FreezePacketLayer.sendBlockState(player, location, clientStateId);
            frozenData.getOutline().addViewer(viewerOf(player));
        }
    }

    /**
     * Unfreezes a block regardless of which player froze it.
     *
     * @return true if the block was frozen
     */
    public static boolean unfreezeBlock(Block block) {
        return unfreeze(FreezeLocation.of(block), true);
    }

    public static void removeAllPlayerFrozenBlock(UUID playerUUID) {
        Set<FreezeLocation> locations = playerFrozenBlocks.remove(playerUUID);
        if (locations == null) {
            return;
        }

        for (FreezeLocation location : locations) {
            unfreeze(location, true);
        }
    }

    public static void removeAllInWorld(World world) {
        UUID worldUUID = world.getUID();
        for (FreezeLocation location : new ArrayList<>(frozenBlocks.keySet())) {
            if (location.worldUUID().equals(worldUUID)) {
                unfreeze(location, false);
            }
        }
        protectedDependentBlocks.keySet().removeIf(location -> location.worldUUID().equals(worldUUID));
    }

    public static void removeOnServerClose() {
        for (FreezeLocation location : new ArrayList<>(frozenBlocks.keySet())) {
            unfreeze(location, false);
        }

        frozenBlocks.clear();
        playerFrozenBlocks.clear();
        frozenChunks.clear();
        protectedDependentBlocks.clear();
    }

    public static boolean isFreezeBlock(Location location) {
        return frozenBlocks.containsKey(FreezeLocation.of(location));
    }

    public static boolean isFreezeBlock(Block block) {
        return frozenBlocks.containsKey(FreezeLocation.of(block));
    }

    /**
     * @return the frozen block at this location, or null when it is not frozen
     */
    public static FreezeBlockData getFrozenBlock(Block block) {
        return frozenBlocks.get(FreezeLocation.of(block));
    }

    static FreezeBlockData getFrozenBlock(FreezeLocation location) {
        return frozenBlocks.get(location);
    }

    static List<FreezeBlockData> getFrozenBlocksInChunk(FreezeLocation.FreezeChunk chunk) {
        Set<FreezeLocation> locations = frozenChunks.get(chunk);
        if (locations == null) {
            return List.of();
        }

        List<FreezeBlockData> result = new ArrayList<>(locations.size());
        for (FreezeLocation location : locations) {
            FreezeBlockData frozenData = frozenBlocks.get(location);
            if (frozenData != null) {
                result.add(frozenData);
            }
        }
        return result;
    }

    public static int getFreezeBlockCount(UUID playerUUID) {
        Set<FreezeLocation> locations = playerFrozenBlocks.get(playerUUID);
        return locations == null ? 0 : locations.size();
    }

    /**
     * Stops sending outline packets to a player that left the server.
     */
    public static void removeViewer(UUID playerUUID) {
        for (FreezeBlockData frozenData : frozenBlocks.values()) {
            removeOutlineViewer(frozenData, playerUUID);
        }
    }

    public static boolean isProtectedDependent(Location location) {
        return protectedDependentBlocks.containsKey(FreezeLocation.of(location));
    }

    static String getProtectedDependentData(FreezeLocation location) {
        return protectedDependentBlocks.get(location);
    }

    public static boolean maintainProtectedDependent(Block block) {
        FreezeLocation freezeLocation = FreezeLocation.of(block);
        String expectedData = protectedDependentBlocks.get(freezeLocation);
        if (expectedData == null) {
            return false;
        }

        if (expectedData.equals(block.getBlockData().getAsString())) {
            return true;
        }

        try {
            block.setBlockData(Bukkit.createBlockData(expectedData), false);
        } catch (IllegalArgumentException ex) {
            protectedDependentBlocks.remove(freezeLocation);
            return false;
        }

        if (!expectedData.equals(block.getBlockData().getAsString())) {
            protectedDependentBlocks.remove(freezeLocation);
            return false;
        }

        return true;
    }

    public static void removeProtectedDependent(Location location) {
        protectedDependentBlocks.remove(FreezeLocation.of(location));
    }

    static VirtualViewer viewerOf(Player player) {
        var playerManager = PacketEvents.getAPI().getPlayerManager();
        return VirtualViewer.of(
                player.getUniqueId(),
                playerManager.getClientVersion(player),
                packet -> playerManager.sendPacket(player, packet)
        );
    }

    static void addOutlineViewer(FreezeBlockData frozenData, VirtualViewer viewer) {
        try {
            // A new viewer object makes VirtualEntities destroy and respawn the outline,
            // which is required after the client dropped it together with its chunk.
            frozenData.getOutline().addViewer(viewer);
        } catch (IllegalStateException ignored) {
            // The block was unfrozen concurrently.
        }
    }

    static void removeOutlineViewer(FreezeBlockData frozenData, UUID viewerUUID) {
        try {
            frozenData.getOutline().removeViewer(viewerUUID);
        } catch (IllegalStateException ignored) {
            // The block was unfrozen concurrently.
        }
    }

    private static boolean unfreeze(FreezeLocation location, boolean schedule) {
        FreezeBlockData frozenData = frozenBlocks.remove(location);
        if (frozenData == null) {
            return false;
        }

        removeFromIndex(playerFrozenBlocks, frozenData.getOwner(), location);
        removeFromIndex(frozenChunks, location.chunk(), location);
        frozenData.getOutline().remove();

        Block block = location.getBlock();
        if (block == null) {
            return true;
        }

        DebugStickPro plugin = DebugStickPro.getInstance();
        if (schedule && plugin != null && plugin.isEnabled()) {
            PlatformScheduler.runAtLocation(plugin, block.getLocation(), () -> restore(block, frozenData));
        } else {
            restore(block, frozenData);
        }
        return true;
    }

    private static void restore(Block block, FreezeBlockData frozenData) {
        // Another plugin or command replaced the anchor while frozen; keep its result.
        if (block.getType() != Material.BARRIER) {
            FreezePacketLayer.resyncBlock(block);
            return;
        }

        Map<FreezeLocation, String> dependentSnapshot = captureDependentNeighborSnapshot(block);

        restoreFrozenBlock(block, frozenData);
        restoreDependentNeighborSnapshot(dependentSnapshot);
        registerProtectedDependentSnapshot(dependentSnapshot);
        restoreDependentNeighborSnapshotLater(dependentSnapshot, 1L, false);
        restoreDependentNeighborSnapshotLater(dependentSnapshot, 2L, true);

        FreezePacketLayer.resyncBlock(block);
    }

    private static VirtualEntity createOutline(Block block) {
        Location center = block.getLocation().add(0.5, 0.5, 0.5);
        VirtualEntity itemDisplay = DebugStickPro.getInstance().getVirtualEntityManager()
                .entity(EntityTypes.ITEM_DISPLAY)
                .uuid(UUID.randomUUID())
                .metadata()
                .build();
        ItemStack itemStack = ItemStack.builder().type(ItemTypes.TINTED_GLASS).amount(1).build();
        itemDisplay.metadata()
                .set(GeneratedEntityMetadataKeys.ItemDisplay.ITEM_STACK, itemStack)
                .set(GeneratedEntityMetadataKeys.Display.SCALE, new Vector3f(1.001F, 1.001F, 1.001F))
                .setFlag(EntityMetadataFlags.GLOWING, true);
        itemDisplay.spawn(SpigotConversionUtil.fromBukkitLocation(center));
        return itemDisplay;
    }

    private static void restoreFrozenBlock(Block block, FreezeBlockData frozenData) {
        boolean restored = frozenData.getOriginalState().update(true, false);

        String expectedBlockData = frozenData.getBlockString();
        if (!restored || !expectedBlockData.equals(block.getBlockData().getAsString())) {
            block.setBlockData(Bukkit.createBlockData(expectedBlockData), false);
        }
    }

    private static Map<FreezeLocation, String> captureDependentNeighborSnapshot(Block center) {
        Map<FreezeLocation, String> snapshot = new HashMap<>();
        for (BlockFace face : ADJACENT_FACES) {
            Block neighbor = center.getRelative(face);
            BlockData blockData = neighbor.getBlockData();
            if (blockData instanceof FaceAttachable || blockData instanceof Rail) {
                snapshot.put(FreezeLocation.of(neighbor), blockData.getAsString());
            }
        }
        return snapshot;
    }

    private static void restoreDependentNeighborSnapshot(Map<FreezeLocation, String> snapshot) {
        for (Map.Entry<FreezeLocation, String> entry : snapshot.entrySet()) {
            restoreDependentNeighbor(entry);
        }
    }

    private static void restoreDependentNeighborSnapshotLater(Map<FreezeLocation, String> snapshot, long delayTicks, boolean cleanupAfter) {
        if (snapshot.isEmpty()) {
            return;
        }

        DebugStickPro plugin = DebugStickPro.getInstance();
        if (plugin == null || !plugin.isEnabled()) {
            return;
        }

        for (Map.Entry<FreezeLocation, String> entry : snapshot.entrySet()) {
            Location location = entry.getKey().getLocation();
            PlatformScheduler.runAtLocationLater(plugin, location, () -> {
                restoreDependentNeighbor(entry);
                if (cleanupAfter) {
                    cleanupVerifiedDependent(entry);
                }
            }, delayTicks);
        }
    }

    private static void restoreDependentNeighbor(Map.Entry<FreezeLocation, String> entry) {
        Block block = entry.getKey().getBlock();
        if (block == null) {
            return;
        }

        String expectedData = entry.getValue();
        if (!expectedData.equals(block.getBlockData().getAsString())) {
            block.setBlockData(Bukkit.createBlockData(expectedData), false);
        }
    }

    private static void cleanupVerifiedDependent(Map.Entry<FreezeLocation, String> entry) {
        Block block = entry.getKey().getBlock();
        if (block == null || entry.getValue().equals(block.getBlockData().getAsString())) {
            protectedDependentBlocks.remove(entry.getKey());
        }
    }

    private static void registerProtectedDependentSnapshot(Map<FreezeLocation, String> snapshot) {
        protectedDependentBlocks.putAll(snapshot);
    }

    private static <K> void addToIndex(Map<K, Set<FreezeLocation>> index, K key, FreezeLocation location) {
        index.compute(key, (ignored, locations) -> {
            Set<FreezeLocation> result = locations == null ? ConcurrentHashMap.newKeySet() : locations;
            result.add(location);
            return result;
        });
    }

    private static <K> void removeFromIndex(Map<K, Set<FreezeLocation>> index, K key, FreezeLocation location) {
        index.computeIfPresent(key, (ignored, locations) -> {
            locations.remove(location);
            return locations.isEmpty() ? null : locations;
        });
    }
}
