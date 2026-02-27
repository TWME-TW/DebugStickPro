package dev.twme.debugstickpro.mode.freeze;

import com.github.retrooper.packetevents.protocol.entity.type.EntityTypes;
import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.protocol.item.type.ItemTypes;
import com.github.retrooper.packetevents.util.Vector3f;
import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.utils.PersistentKeys;
import dev.twme.debugstickpro.utils.SendFakeBarrier;
import io.github.retrooper.packetevents.util.SpigotConversionUtil;
import me.tofaa.entitylib.EntityLib;
import me.tofaa.entitylib.meta.display.BlockDisplayMeta;
import me.tofaa.entitylib.meta.display.ItemDisplayMeta;
import me.tofaa.entitylib.wrapper.WrapperEntity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.FaceAttachable;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class FreezeBlockManager {
    private static final BlockFace[] ADJACENT_FACES = {
            BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN
    };

    private static final HashSet<FreezeLocation> freezeBlockLocations = new HashSet<>();
    private static final HashMap<UUID, ArrayList<FreezeBlockData>> playerFrozenBlockData = new HashMap<>();

    public static void freezeBlock(UUID playerUUID, Block block) {
        Location location = block.getLocation();
        FreezeLocation freezeLocation = new FreezeLocation(location);
        if (freezeBlockLocations.contains(freezeLocation)) {
            return;
        }

        // create player freeze block data list
        if (!playerFrozenBlockData.containsKey(playerUUID)) {
            playerFrozenBlockData.put(playerUUID, new ArrayList<>());
        }
        ArrayList<FreezeBlockData> freezeBlockList = playerFrozenBlockData.get(playerUUID);

        // create and add freeze block to freezeBlockList
        FreezeBlockData freezeBlock = freezeBlockBuilder(playerUUID, location);
        freezeBlockList.add(freezeBlock);
        SendFakeBarrier.sendFakeBarrier(playerUUID, block.getLocation());

        // add freeze block recode to playerFreezeBlockDataList
        playerFrozenBlockData.put(playerUUID, freezeBlockList);

        // add freeze block to freezeBlockLocations
        freezeBlockLocations.add(freezeLocation);
    }


    /*
       when player right-click a freeze block , unfreeze it!
     */
    public static void removeOneBlock(UUID playerUUID, Block block) {

        // no data
        if (!playerFrozenBlockData.containsKey(playerUUID)) {
            return;
        }

        FreezeLocation freezeLocation = new FreezeLocation(block.getLocation());
        if (!freezeBlockLocations.contains(freezeLocation)) {
            return;
        }

        ArrayList<FreezeBlockData> freezeBlocks = playerFrozenBlockData.get(playerUUID);

        Iterator<FreezeBlockData> iterator = freezeBlocks.iterator();
        while (iterator.hasNext()) {
            FreezeBlockData f = iterator.next();
            if (f.getBlock().getLocation().equals(block.getLocation())) {
                Map<FreezeLocation, String> attachableSnapshot = captureAttachableNeighborSnapshot(block);

                iterator.remove();
                freezeBlockLocations.remove(freezeLocation);
                if (freezeBlocks.isEmpty()) {
                    playerFrozenBlockData.remove(playerUUID);
                }

                restoreBlockDataIfChanged(block, f.getBlockString());
                SendFakeBarrier.removeFakeBarrier(playerUUID, block.getLocation());
                Objects.requireNonNull(EntityLib.getApi().getEntity(f.getItemDisplay())).remove();
                Objects.requireNonNull(EntityLib.getApi().getEntity(f.getBlockDisplay())).remove();
                restoreAttachableNeighborSnapshot(attachableSnapshot);
                restoreAttachableNeighborSnapshotNextTick(attachableSnapshot);
                resendRealBlockToPlayer(playerUUID, block);
                resendAttachableNeighborSnapshotToPlayer(playerUUID, attachableSnapshot);
                break;
            }
        }
    }

    // when player left-click, remove all this player frozen blocks
    public static void removeAllPlayerFrozenBlock(UUID playerUUID) {
        ArrayList<FreezeBlockData> freezeBlocks = playerFrozenBlockData.remove(playerUUID);
        if (freezeBlocks == null) {
            return;
        }
        for (FreezeBlockData f : freezeBlocks) {
            Map<FreezeLocation, String> attachableSnapshot = captureAttachableNeighborSnapshot(f.getBlock());
            FreezeLocation freezeLocation = new FreezeLocation(f.getBlock().getLocation());
            freezeBlockLocations.remove(freezeLocation);

            restoreBlockDataIfChanged(f.getBlock(), f.getBlockString());
            SendFakeBarrier.removeFakeBarrier(playerUUID, f.getBlock().getLocation());
            Objects.requireNonNull(EntityLib.getApi().getEntity(f.getItemDisplay())).remove();
            Objects.requireNonNull(EntityLib.getApi().getEntity(f.getBlockDisplay())).remove();
            restoreAttachableNeighborSnapshot(attachableSnapshot);
            restoreAttachableNeighborSnapshotNextTick(attachableSnapshot);
            resendRealBlockToPlayer(playerUUID, f.getBlock());
            resendAttachableNeighborSnapshotToPlayer(playerUUID, attachableSnapshot);

        }
    }

    public static boolean isFreezeBlock(Location location) {
        FreezeLocation freezeLocation = new FreezeLocation(location);
        return freezeBlockLocations.contains(freezeLocation);
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
        if (!freezeBlockLocations.isEmpty()) {
            for (FreezeLocation freezeLocation : freezeBlockLocations) {
                freezeLocation.getLocation().getBlock().setType(Material.AIR, false);
            }
        }
    }

    public static int getFreezeBlockCount(UUID playerUUID) {
        if (!playerFrozenBlockData.containsKey(playerUUID)) {
            return 0;
        }
        return playerFrozenBlockData.get(playerUUID).size();
    }

    public static FreezeBlockData freezeBlockBuilder(UUID playerUUID, Location location) {

        Block block = location.getBlock();
        // offset location
        Location entityLocation = new Location(location.getWorld(), location.getX() + 0.5, location.getY() + 0.5, location.getZ() + 0.5);

        UUID itemDisplayUUID = UUID.randomUUID();
        UUID blockDisplayUUID = UUID.randomUUID();
        WrapperEntity wrapperItemDisplayEntity = new WrapperEntity(itemDisplayUUID, EntityTypes.ITEM_DISPLAY);

        ItemDisplayMeta itemDisplayMeta = (ItemDisplayMeta) wrapperItemDisplayEntity.getEntityMeta();


        ItemStack itemStack = ItemStack.builder().type(ItemTypes.TINTED_GLASS).amount(1).build();

        itemDisplayMeta.setItem(itemStack);
        itemDisplayMeta.setScale(new Vector3f(1.001F, 1.001F, 1.001F));
        itemDisplayMeta.setGlowing(true);

        addViewer(wrapperItemDisplayEntity);

        wrapperItemDisplayEntity.spawn(SpigotConversionUtil.fromBukkitLocation(entityLocation));

        // spawn block display
        Location location1 = SpecialBlockFilter.filter(block.getType(), location);

        WrapperEntity wrapperBlockDisplayEntity = new WrapperEntity(blockDisplayUUID, EntityTypes.BLOCK_DISPLAY);
        BlockDisplayMeta bdMeta = (BlockDisplayMeta) wrapperBlockDisplayEntity.getEntityMeta();
        bdMeta.setBlockId(SpigotConversionUtil.fromBukkitBlockData(block.getBlockData()).getGlobalId());
        wrapperBlockDisplayEntity.spawn(SpigotConversionUtil.fromBukkitLocation(location1));
        addViewer(wrapperBlockDisplayEntity);

        return new FreezeBlockData(itemDisplayUUID, blockDisplayUUID, block);
    }

    private static void addViewer(WrapperEntity wrapperEntity) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            wrapperEntity.addViewer(player.getUniqueId());
        }
    }

    private static void restoreBlockDataIfChanged(Block block, String blockString) {
        if (!block.getBlockData().getAsString().equals(blockString)) {
            block.setBlockData(Bukkit.createBlockData(blockString), false);
        }
    }

    private static void resendRealBlockToPlayer(UUID playerUUID, Block block) {
        Player player = Bukkit.getPlayer(playerUUID);
        if (player == null) {
            return;
        }

        player.sendBlockChange(block.getLocation(), block.getBlockData());

        DebugStickPro plugin = DebugStickPro.getInstance();
        if (plugin == null || !plugin.isEnabled()) {
            return;
        }

        // One tick delay ensures fake-stage removal is fully applied before resyncing.
        Bukkit.getScheduler().runTask(plugin, () ->
                player.sendBlockChange(block.getLocation(), block.getBlockData())
        );
    }

    private static Map<FreezeLocation, String> captureAttachableNeighborSnapshot(Block center) {
        Map<FreezeLocation, String> snapshot = new HashMap<>();
        for (BlockFace face : ADJACENT_FACES) {
            Block neighbor = center.getRelative(face);
            if (neighbor.getBlockData() instanceof FaceAttachable) {
                snapshot.put(new FreezeLocation(neighbor.getLocation()), neighbor.getBlockData().getAsString());
            }
        }
        return snapshot;
    }

    private static void restoreAttachableNeighborSnapshot(Map<FreezeLocation, String> snapshot) {
        for (Map.Entry<FreezeLocation, String> entry : snapshot.entrySet()) {
            Block block = entry.getKey().getLocation().getBlock();
            String expectedData = entry.getValue();
            if (!block.getBlockData().getAsString().equals(expectedData)) {
                block.setBlockData(Bukkit.createBlockData(expectedData), false);
            }
        }
    }

    private static void restoreAttachableNeighborSnapshotNextTick(Map<FreezeLocation, String> snapshot) {
        if (snapshot.isEmpty()) {
            return;
        }

        DebugStickPro plugin = DebugStickPro.getInstance();
        if (plugin == null || !plugin.isEnabled()) {
            return;
        }

        Bukkit.getScheduler().runTask(plugin, () -> restoreAttachableNeighborSnapshot(snapshot));
    }

    private static void resendAttachableNeighborSnapshotToPlayer(UUID playerUUID, Map<FreezeLocation, String> snapshot) {
        if (snapshot.isEmpty()) {
            return;
        }

        Player player = Bukkit.getPlayer(playerUUID);
        if (player == null) {
            return;
        }

        for (FreezeLocation freezeLocation : snapshot.keySet()) {
            Block neighbor = freezeLocation.getLocation().getBlock();
            player.sendBlockChange(neighbor.getLocation(), neighbor.getBlockData());
        }

        DebugStickPro plugin = DebugStickPro.getInstance();
        if (plugin == null || !plugin.isEnabled()) {
            return;
        }

        Bukkit.getScheduler().runTask(plugin, () -> {
            for (FreezeLocation freezeLocation : snapshot.keySet()) {
                Block neighbor = freezeLocation.getLocation().getBlock();
                player.sendBlockChange(neighbor.getLocation(), neighbor.getBlockData());
            }
        });
    }
}
