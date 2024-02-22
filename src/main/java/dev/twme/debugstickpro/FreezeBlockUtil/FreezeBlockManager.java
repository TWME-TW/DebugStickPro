package dev.twme.debugstickpro.FreezeBlockUtil;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;

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
        Location entityLocation = new Location(location.getWorld(), location.getX() + 0.5, location.getY() + 0.5, location.getZ() + 0.5);
        Entity entity = location.getWorld().spawnEntity(entityLocation, EntityType.ITEM_DISPLAY);
        ItemDisplay itemDisplay = (ItemDisplay) entity;
        ItemStack itemStack = new ItemStack(Material.RED_WOOL, 1);
        itemDisplay.setItemStack(itemStack);

        entity.setGlowing(true);
        if (!freezeBlockData.containsKey(playerUUID)) {
            freezeBlockData.put(playerUUID, new ArrayList<>());
        }
        ArrayList<FreezeBlockData> freezeBlockList = freezeBlockData.get(playerUUID);
        FreezeBlockData freezeBlock = new FreezeBlockData(entity, block);
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
                f.getEntity().remove();
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
                f.getEntity().remove();
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
}
