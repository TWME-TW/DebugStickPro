package dev.twme.debugstickpro.mode.freeze;

import com.github.retrooper.packetevents.protocol.entity.type.EntityTypes;
import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.protocol.item.type.ItemTypes;
import com.github.retrooper.packetevents.util.Vector3f;
import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.scheduler.PlatformScheduler;
import dev.twme.debugstickpro.utils.PersistentKeys;
import io.github.retrooper.packetevents.util.SpigotConversionUtil;
import io.github.twme.virtualentities.VirtualEntity;
import io.github.twme.virtualentities.VirtualEntityManager;
import io.github.twme.virtualentities.VirtualViewer;
import io.github.twme.virtualentities.metadata.EntityMetadataFlags;
import io.github.twme.virtualentities.metadata.GeneratedEntityMetadataKeys;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.FaceAttachable;
import org.bukkit.block.data.Rail;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class FreezeBlockManager {
    private static final BlockFace[] ADJACENT_FACES = {
            BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN
    };

    private static final Set<FreezeLocation> freezeBlockLocations = ConcurrentHashMap.newKeySet();
    private static final Map<UUID, CopyOnWriteArrayList<FreezeBlockData>> playerFrozenBlockData = new ConcurrentHashMap<>();
    private static final Map<FreezeLocation, String> protectedDependentBlocks = new ConcurrentHashMap<>();

    public static void freezeBlock(UUID playerUUID, Block block) {
        FreezeLocation freezeLocation = new FreezeLocation(block.getLocation());
        if (freezeBlockLocations.contains(freezeLocation)) {
            return;
        }

        CopyOnWriteArrayList<FreezeBlockData> freezeBlockList = playerFrozenBlockData.computeIfAbsent(
                playerUUID,
                ignored -> new CopyOnWriteArrayList<>()
        );
        FreezeBlockData freezeBlock = freezeBlockBuilder(block.getLocation());
        freezeBlockList.add(freezeBlock);
        freezeBlockLocations.add(freezeLocation);

        block.setBlockData(Material.BARRIER.createBlockData(), false);
    }

    public static void removeOneBlock(UUID playerUUID, Block block) {
        CopyOnWriteArrayList<FreezeBlockData> freezeBlocks = playerFrozenBlockData.get(playerUUID);
        if (freezeBlocks == null) {
            return;
        }

        FreezeLocation freezeLocation = new FreezeLocation(block.getLocation());
        if (!freezeBlockLocations.contains(freezeLocation)) {
            return;
        }

        for (FreezeBlockData frozenData : freezeBlocks) {
            if (!frozenData.getBlock().getLocation().equals(block.getLocation())) {
                continue;
            }

            Map<FreezeLocation, String> dependentSnapshot = captureDependentNeighborSnapshot(block);

            restoreFrozenBlock(block, frozenData);
            restoreDependentNeighborSnapshot(dependentSnapshot);
            registerProtectedDependentSnapshot(dependentSnapshot);
            restoreDependentNeighborSnapshotLater(dependentSnapshot, 1L, false);
            restoreDependentNeighborSnapshotLater(dependentSnapshot, 2L, true);

            removeDisplayEntity(frozenData.getItemDisplay());
            removeDisplayEntity(frozenData.getBlockDisplay());

            freezeBlockLocations.remove(freezeLocation);
            freezeBlocks.remove(frozenData);
            FreezePacketLayer.resyncBlock(block);
            if (freezeBlocks.isEmpty()) {
                playerFrozenBlockData.remove(playerUUID);
            }
            return;
        }
    }

    public static void removeAllPlayerFrozenBlock(UUID playerUUID) {
        CopyOnWriteArrayList<FreezeBlockData> freezeBlocks = playerFrozenBlockData.remove(playerUUID);
        if (freezeBlocks == null) {
            return;
        }

        for (FreezeBlockData frozenData : freezeBlocks) {
            Block block = frozenData.getBlock();
            Map<FreezeLocation, String> dependentSnapshot = captureDependentNeighborSnapshot(block);

            restoreFrozenBlock(block, frozenData);
            restoreDependentNeighborSnapshot(dependentSnapshot);
            registerProtectedDependentSnapshot(dependentSnapshot);
            restoreDependentNeighborSnapshotLater(dependentSnapshot, 1L, false);
            restoreDependentNeighborSnapshotLater(dependentSnapshot, 2L, true);

            removeDisplayEntity(frozenData.getItemDisplay());
            removeDisplayEntity(frozenData.getBlockDisplay());
            freezeBlockLocations.remove(new FreezeLocation(block.getLocation()));
            FreezePacketLayer.resyncBlock(block);
        }
    }

    public static boolean isFreezeBlock(Location location) {
        return freezeBlockLocations.contains(new FreezeLocation(location));
    }

    public static void removeOnChunkLoadOrUnload(Entity entity) {
        PersistentDataContainer container = entity.getPersistentDataContainer();
        if (!container.has(PersistentKeys.FREEZE_BLOCK_DISPLAY, PersistentDataType.STRING)) {
            return;
        }

        UUID playerUUID = UUID.fromString(Objects.requireNonNull(container.get(PersistentKeys.FREEZE_BLOCK_DISPLAY, PersistentDataType.STRING)));
        removeOneBlock(playerUUID, entity.getLocation().getBlock());
    }

    public static void removeOnServerClose() {
        if (!playerFrozenBlockData.isEmpty()) {
            for (UUID playerUUID : new ArrayList<>(playerFrozenBlockData.keySet())) {
                removeAllPlayerFrozenBlock(playerUUID);
            }
        }

        freezeBlockLocations.clear();
        protectedDependentBlocks.clear();
    }

    public static int getFreezeBlockCount(UUID playerUUID) {
        CopyOnWriteArrayList<FreezeBlockData> freezeBlocks = playerFrozenBlockData.get(playerUUID);
        return freezeBlocks == null ? 0 : freezeBlocks.size();
    }

    public static boolean isProtectedDependent(Location location) {
        return protectedDependentBlocks.containsKey(new FreezeLocation(location));
    }

    public static String getProtectedDependentData(Location location) {
        return protectedDependentBlocks.get(new FreezeLocation(location));
    }

    public static boolean maintainProtectedDependent(Block block) {
        FreezeLocation freezeLocation = new FreezeLocation(block.getLocation());
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
        protectedDependentBlocks.remove(new FreezeLocation(location));
    }

    private static FreezeBlockData freezeBlockBuilder(Location location) {
        Block block = location.getBlock();
        BlockState originalState = block.getState();

        Location entityLocation = new Location(location.getWorld(), location.getX() + 0.5, location.getY() + 0.5, location.getZ() + 0.5);

        UUID itemDisplayUUID = UUID.randomUUID();
        UUID blockDisplayUUID = UUID.randomUUID();

        VirtualEntityManager entityManager = DebugStickPro.getInstance().getVirtualEntityManager();
        VirtualEntity itemDisplay = entityManager.entity(EntityTypes.ITEM_DISPLAY)
                .uuid(itemDisplayUUID)
                .metadata()
                .build();
        ItemStack itemStack = ItemStack.builder().type(ItemTypes.TINTED_GLASS).amount(1).build();
        itemDisplay.metadata()
                .set(GeneratedEntityMetadataKeys.ItemDisplay.ITEM_STACK, itemStack)
                .set(GeneratedEntityMetadataKeys.Display.SCALE, new Vector3f(1.001F, 1.001F, 1.001F))
                .setFlag(EntityMetadataFlags.GLOWING, true);
        addViewers(itemDisplay);
        itemDisplay.spawn(SpigotConversionUtil.fromBukkitLocation(entityLocation));

        Location blockDisplayLocation = SpecialBlockFilter.filter(block.getType(), location);
        VirtualEntity blockDisplay = entityManager.entity(EntityTypes.BLOCK_DISPLAY)
                .uuid(blockDisplayUUID)
                .metadata()
                .build();
        blockDisplay.metadata().set(
                GeneratedEntityMetadataKeys.BlockDisplay.BLOCK_STATE,
                SpigotConversionUtil.fromBukkitBlockData(block.getBlockData()).getGlobalId()
        );
        addViewers(blockDisplay);
        blockDisplay.spawn(SpigotConversionUtil.fromBukkitLocation(blockDisplayLocation));

        return new FreezeBlockData(itemDisplayUUID, blockDisplayUUID, block, originalState);
    }

    private static void addViewers(VirtualEntity virtualEntity) {
        var playerManager = com.github.retrooper.packetevents.PacketEvents.getAPI().getPlayerManager();
        for (Player player : Bukkit.getOnlinePlayers()) {
            VirtualViewer viewer = VirtualViewer.of(
                    player.getUniqueId(),
                    playerManager.getClientVersion(player),
                    packet -> playerManager.sendPacket(player, packet)
            );
            virtualEntity.addViewer(viewer);
        }
    }

    private static void restoreFrozenBlock(Block block, FreezeBlockData frozenData) {
        boolean restored = false;

        BlockState originalState = frozenData.getOriginalState();
        if (originalState != null) {
            restored = originalState.update(true, false);
        }

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
                snapshot.put(new FreezeLocation(neighbor.getLocation()), blockData.getAsString());
            }
        }
        return snapshot;
    }

    private static void restoreDependentNeighborSnapshot(Map<FreezeLocation, String> snapshot) {
        for (Map.Entry<FreezeLocation, String> entry : snapshot.entrySet()) {
            Block block = entry.getKey().getLocation().getBlock();
            String expectedData = entry.getValue();
            if (!expectedData.equals(block.getBlockData().getAsString())) {
                block.setBlockData(Bukkit.createBlockData(expectedData), false);
            }
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
        Block block = entry.getKey().getLocation().getBlock();
        String expectedData = entry.getValue();
        if (!expectedData.equals(block.getBlockData().getAsString())) {
            block.setBlockData(Bukkit.createBlockData(expectedData), false);
        }
    }

    private static void cleanupVerifiedDependent(Map.Entry<FreezeLocation, String> entry) {
        if (entry.getValue().equals(entry.getKey().getLocation().getBlock().getBlockData().getAsString())) {
            protectedDependentBlocks.remove(entry.getKey());
        }
    }

    private static void registerProtectedDependentSnapshot(Map<FreezeLocation, String> snapshot) {
        protectedDependentBlocks.putAll(snapshot);
    }

    private static void removeDisplayEntity(UUID entityUUID) {
        DebugStickPro.getInstance().getVirtualEntityManager().find(entityUUID).ifPresent(VirtualEntity::remove);
    }
}
