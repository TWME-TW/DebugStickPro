package dev.twme.debugstickpro.mode.freeze;

import dev.twme.debugstickpro.events.FreezeBlockEvent;
import dev.twme.debugstickpro.util.PersistentKeys;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Transformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class FreezeBlockManager {
    private static HashSet<FreezeLocation> freezeBlockLocations = new HashSet<>();
    private static HashMap<UUID, ArrayList<FreezeBlockData>> freezeBlockData = new HashMap<>();

    public static void addBlock(UUID playerUUID, Block block) {
        Location location = block.getLocation();
        FreezeLocation freezeLocation = new FreezeLocation(location);
        if (freezeBlockLocations.contains(freezeLocation)) {
            return;
        }
        FreezeBlockEvent event = new FreezeBlockEvent(playerUUID, block);
        if (event.isCancelled()){
            return;
        }
        Location entityLocation = new Location(location.getWorld(), location.getX() + 0.5, location.getY() + 0.5, location.getZ() + 0.5);


        // spawn item display

        Entity entity = location.getWorld().spawnEntity(entityLocation, EntityType.ITEM_DISPLAY);
        ItemDisplay itemDisplay = (ItemDisplay) entity;
        ItemStack itemStack = new ItemStack(Material.TINTED_GLASS, 1);
        itemDisplay.setItemStack(itemStack);

        Transformation transformation = itemDisplay.getTransformation();
        transformation.getScale().set(1.0001F);
        itemDisplay.setTransformation(transformation);

        entity.setGlowing(true);
        entity.setInvulnerable(true);

        // Entity entity = location.getWorld().spawnEntity(entityLocation,EntityType.SHULKER);
        // entity.setGlowing(true);
        // entity.setInvulnerable(true);
        // LivingEntity livingEntity = (LivingEntity) entity;
        // livingEntity.setInvisible(true);
        // livingEntity.setAI(false);
        // livingEntity.setSilent(true);
        // livingEntity.setCollidable(false);
        // livingEntity.setGravity(false);


        // spawn block display
        Location location1 = SpecialBlockFilter.filter(block.getType(), location);

        Entity blockEntity = location.getWorld().spawnEntity(location1, EntityType.BLOCK_DISPLAY);
        BlockDisplay blockDisplay = (BlockDisplay) blockEntity;
        blockDisplay.setBlock(block.getBlockData());
        blockEntity.setInvulnerable(true);

        entity.getPersistentDataContainer().set(PersistentKeys.FREEZE_BLOCK_DISPLAY, PersistentDataType.STRING, playerUUID.toString());
        blockEntity.getPersistentDataContainer().set(PersistentKeys.FREEZE_BLOCK_DISPLAY, PersistentDataType.STRING, playerUUID.toString());

        if (!freezeBlockData.containsKey(playerUUID)) {
            freezeBlockData.put(playerUUID, new ArrayList<>());
        }

        ArrayList<FreezeBlockData> freezeBlockList = freezeBlockData.get(playerUUID);
        FreezeBlockData freezeBlock = new FreezeBlockData(entity, blockDisplay, block);
        freezeBlockList.add(freezeBlock);
        block.setType(Material.BARRIER, false);
        block.getState().update();
        freezeBlockData.put(playerUUID, freezeBlockList);
        freezeBlockLocations.add(freezeLocation);
    }

    public static void removeBlock(UUID playerUUID, Block block) {
        if (!freezeBlockData.containsKey(playerUUID)) {
            return;
        }

        FreezeLocation freezeLocation = new FreezeLocation(block.getLocation());
        if (!freezeBlockLocations.contains(freezeLocation)) {
            return;
        }

        ArrayList<FreezeBlockData> freezeBlocks = freezeBlockData.get(playerUUID);

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

    public static void removeAllBlock(UUID playerUUID) {
        if (!freezeBlockData.containsKey(playerUUID)) {
            return;
        }
        ArrayList<FreezeBlockData> freezeBlocks = freezeBlockData.get(playerUUID);
        for (FreezeBlockData f : freezeBlocks) {

            FreezeLocation freezeLocation = new FreezeLocation(f.getBlock().getLocation());

            if (f.getBlock().getLocation().getBlock().getType() == Material.BARRIER) {
                f.getItemDisplay().remove();
                f.getBlockDisplay().remove();
                f.getBlock().setBlockData(Bukkit.createBlockData(f.getBlockString()), false);
                f.getBlock().getState().update();

                freezeBlockLocations.remove(freezeLocation);
            } else {
                freezeBlockLocations.remove(freezeLocation);
            }
        }
        freezeBlockData.remove(playerUUID);
    }

    public static boolean isFreezeBlock(Location location) {
        FreezeLocation freezeLocation = new FreezeLocation(location);
        return freezeBlockLocations.contains(freezeLocation);
    }
    public static void removeOnChunkLoadOrUnload(Entity entity){
        PersistentDataContainer container = entity.getPersistentDataContainer();
        if (!container.has(PersistentKeys.FREEZE_BLOCK_DISPLAY , PersistentDataType.STRING)) {
            return;
        }
        UUID playerUUID = UUID.fromString(container.get(PersistentKeys.FREEZE_BLOCK_DISPLAY, PersistentDataType.STRING));

        if (!freezeBlockData.containsKey(playerUUID)) {
            removeNullPlayerEntityAndBlock(entity);
            return;
        }
        removeBlock(playerUUID, entity.getLocation().getBlock());

    }

    private static void removeNullPlayerEntityAndBlock(Entity entity){
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
            }
        }

        if (location.getBlock().getType() == Material.BARRIER) {
            location.getBlock().setType(Material.AIR, false);
        }

        FreezeLocation freezeLocation = new FreezeLocation(location);
        freezeBlockLocations.remove(freezeLocation);
    }

    public static void removeOnServerClose(){
        if (!freezeBlockData.isEmpty()) {
            for (UUID playerUUID : freezeBlockData.keySet()) {
                removeAllBlock(playerUUID);
            }
        }
        if (!freezeBlockLocations.isEmpty()) {
            for (FreezeLocation freezeLocation : freezeBlockLocations) {
                freezeLocation.getLocation().getBlock().setType(Material.AIR, false);
            }
        }
    }
}
