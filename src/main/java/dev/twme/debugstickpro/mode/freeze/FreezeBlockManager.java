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
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class FreezeBlockManager {
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

        for (FreezeBlockData f : freezeBlocks) {
            if (f.getBlock().getLocation().equals(block.getLocation())) {
                block.setBlockData(Bukkit.createBlockData(f.getBlockString()), false);
                Objects.requireNonNull(EntityLib.getApi().getEntity(f.getItemDisplay())).remove();
                Objects.requireNonNull(EntityLib.getApi().getEntity(f.getBlockDisplay())).remove();
                freezeBlocks.remove(f);
                freezeBlockLocations.remove(freezeLocation);
                break;
            }
        }
        block.getState().update();
    }

    // when player left-click, remove all this player frozen blocks
    public static void removeAllPlayerFrozenBlock(UUID playerUUID) {
        if (!playerFrozenBlockData.containsKey(playerUUID)) {
            return;
        }
        ArrayList<FreezeBlockData> freezeBlocks = playerFrozenBlockData.get(playerUUID);
        for (FreezeBlockData f : freezeBlocks) {

            FreezeLocation freezeLocation = new FreezeLocation(f.getBlock().getLocation());

            Objects.requireNonNull(EntityLib.getApi().getEntity(f.getItemDisplay())).remove();
            Objects.requireNonNull(EntityLib.getApi().getEntity(f.getBlockDisplay())).remove();
            f.getBlock().setBlockData(Bukkit.createBlockData(f.getBlockString()), false);
            f.getBlock().getState().update();
            freezeBlockLocations.remove(freezeLocation);

        }
        playerFrozenBlockData.remove(playerUUID);
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

        if (!playerFrozenBlockData.containsKey(playerUUID)) {
            removeNullPlayerEntityAndBlock(entity);
            return;
        }
        removeOneBlock(playerUUID, entity.getLocation().getBlock());

    }

    // remove freeze block when no freeze block data on player
    private static void removeNullPlayerEntityAndBlock(Entity entity) {
        if (entity.getType() != EntityType.ITEM_DISPLAY || entity.getType() != EntityType.BLOCK_DISPLAY) {
            return;
        }

        Material material = entity.getLocation().getBlock().getType();
        Location location = entity.getLocation();

        boolean notAir = material != Material.AIR && material != Material.CAVE_AIR && material != Material.VOID_AIR;

        if (entity.getType() == EntityType.ITEM_DISPLAY) {
            if (notAir) {
                entity.remove();
            }
        }

        if (entity.getType() == EntityType.BLOCK_DISPLAY) {
            if (notAir) {
                entity.remove();

            } else {
                BlockDisplay blockDisplay = (BlockDisplay) entity;
                BlockData blockData = blockDisplay.getBlock();
                location.getBlock().setBlockData(blockData, false);
                entity.remove();
                location.getBlock().getState().update();
            }
        }

        FreezeLocation freezeLocation = new FreezeLocation(location);
        freezeBlockLocations.remove(freezeLocation);
    }

    public static void removeOnServerClose() {
        if (!playerFrozenBlockData.isEmpty()) {
            for (UUID playerUUID : playerFrozenBlockData.keySet()) {
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

        return new FreezeBlockData(blockDisplayUUID, itemDisplayUUID, block);
    }

    private static void addViewer(WrapperEntity wrapperEntity) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            wrapperEntity.addViewer(player.getUniqueId());
        }
    }
}
