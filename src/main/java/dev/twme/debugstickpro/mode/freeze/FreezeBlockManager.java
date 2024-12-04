package dev.twme.debugstickpro.mode.freeze;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.utils.PersistentKeys;
import fr.skytasul.glowingentities.GlowingBlocks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Transformation;

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

        // set block to barrier
        block.setType(Material.BARRIER, false);
        block.getState().update();

        // add freeze block recode to playerFreezeBlockDataList
        playerFrozenBlockData.put(playerUUID, freezeBlockList);

        // add freeze block to freezeBlockLocations
        freezeBlockLocations.add(freezeLocation);
    }


    /*
       when player right-click a freeze block , unfreeze it!
     */
    public static void removeOneBlock(UUID playerUUID, Block block) {

        try {
            DebugStickPro.getInstance().getGlowingBlocks().unsetGlowing(block.getLocation(), Objects.requireNonNull(Bukkit.getPlayer(playerUUID)));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
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
            if (f.getBlock().getType() != Material.BARRIER) {
                freezeBlockLocations.remove(freezeLocation);
                continue;
            }
            if (f.getBlock().getLocation().equals(block.getLocation())) {
                block.setBlockData(Bukkit.createBlockData(f.getBlockString()), false);
                f.getItemDisplay().remove();
                f.getBlockDisplay().remove();
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

            try {
                DebugStickPro.getInstance().getGlowingBlocks().unsetGlowing(f.getBlock().getLocation(), Objects.requireNonNull(Bukkit.getPlayer(playerUUID)));
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }

            f.getItemDisplay().remove();
            f.getBlockDisplay().remove();
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

        boolean notBarrierOrAir = material != Material.BARRIER && material != Material.AIR && material != Material.CAVE_AIR && material != Material.VOID_AIR;

        if (entity.getType() == EntityType.ITEM_DISPLAY) {
            if (notBarrierOrAir) {
                entity.remove();
            }
        }

        if (entity.getType() == EntityType.BLOCK_DISPLAY) {
            if (notBarrierOrAir) {
                entity.remove();

            } else {
                BlockDisplay blockDisplay = (BlockDisplay) entity;
                BlockData blockData = blockDisplay.getBlock();
                location.getBlock().setBlockData(blockData, false);
                entity.remove();
                location.getBlock().getState().update();
            }
        }

        if (location.getBlock().getType() == Material.BARRIER) {
            location.getBlock().setType(Material.AIR, false);
            location.getBlock().getState().update();
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
        // spawn item display

        Entity entity = location.getWorld().spawnEntity(entityLocation, EntityType.ITEM_DISPLAY);
        ItemDisplay itemDisplay = (ItemDisplay) entity;
        ItemStack itemStack = new ItemStack(Material.TINTED_GLASS, 1);
        itemDisplay.setItemStack(itemStack);

        Transformation transformation = itemDisplay.getTransformation();
        transformation.getScale().set(1.0001F);
        itemDisplay.setTransformation(transformation);

        // entity.setGlowing(true);
        GlowingBlocks glowingBlocks = DebugStickPro.getInstance().getGlowingBlocks();
        try {
            glowingBlocks.setGlowing(location, Bukkit.getPlayer(playerUUID), ChatColor.WHITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        entity.setInvulnerable(true);

        // spawn block display
        Location location1 = SpecialBlockFilter.filter(block.getType(), location);

        Entity blockEntity = location.getWorld().spawnEntity(location1, EntityType.BLOCK_DISPLAY);
        BlockDisplay blockDisplay = (BlockDisplay) blockEntity;
        blockDisplay.setBlock(block.getBlockData());
        blockEntity.setInvulnerable(true);

        // add persistent data
        entity.getPersistentDataContainer().set(PersistentKeys.FREEZE_BLOCK_DISPLAY, PersistentDataType.STRING, playerUUID.toString());
        blockEntity.getPersistentDataContainer().set(PersistentKeys.FREEZE_BLOCK_DISPLAY, PersistentDataType.STRING, playerUUID.toString());

        return new FreezeBlockData(blockDisplay, itemDisplay, block);
    }
}
