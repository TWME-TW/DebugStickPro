package dev.twme.debugstickpro.FreezeBlockUtil;

import dev.twme.debugstickpro.events.FreezeBlockEvent;
import dev.twme.debugstickpro.util.PersistentKeys;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
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
    private static HashSet<Location> freezeBlockLocations = new HashSet<>();
    private static HashMap<UUID, ArrayList<FreezeBlockData>> freezeBlockData = new HashMap<>();

    public static void addBlock(UUID playerUUID, Block block) {
        Location location = block.getLocation();
        if (freezeBlockLocations.contains(location)) {
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

        Entity blockEntity = location.getWorld().spawnEntity(location, EntityType.BLOCK_DISPLAY);
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
        freezeBlockLocations.add(block.getLocation());
    }

    public static void removeBlock(UUID playerUUID, Block block) {
        if (!freezeBlockData.containsKey(playerUUID)) {
            return;
        }

        if (!freezeBlockLocations.contains(block.getLocation())) {
            return;
        }

        ArrayList<FreezeBlockData> freezeBlocks = freezeBlockData.get(playerUUID);

        for (FreezeBlockData f : freezeBlocks) {
            if (f.getBlock().getType() != Material.BARRIER) {
                freezeBlockLocations.remove(f.getBlock().getLocation());
                continue;
            }
            if (f.getBlock().getLocation().equals(block.getLocation())) {
                block.setBlockData(Bukkit.createBlockData(f.getBlockString()), false);
                f.getItemDisplay().remove();
                f.getBlockDisplay().remove();
                freezeBlocks.remove(f);
                freezeBlockLocations.remove(block.getLocation());
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
            if (f.getBlock().getLocation().getBlock().getType() == Material.BARRIER) {
                f.getItemDisplay().remove();
                f.getBlockDisplay().remove();
                f.getBlock().setBlockData(Bukkit.createBlockData(f.getBlockString()), false);
                f.getBlock().getState().update();
                freezeBlockLocations.remove(f.getBlock().getLocation());
            } else {
                freezeBlockLocations.remove(f.getBlock().getLocation());
            }
        }
        freezeBlockData.remove(playerUUID);
    }

    public static boolean isFreezeBlock(Location location) {
        return freezeBlockLocations.contains(location);
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
        Location location = entity.getLocation();
        if (location.getBlock().getType() != Material.BARRIER) {
            entity.remove();
            return;
        }
        entity.remove();
        location.getBlock().setType(Material.AIR, false);
        freezeBlockLocations.remove(location);
    }

    public static void removeOnServerClose(){
        for (UUID playerUUID : freezeBlockData.keySet()) {
            removeAllBlock(playerUUID);
        }
        for (Location location : freezeBlockLocations) {
            location.getBlock().setType(Material.AIR, false);
        }
    }
}
