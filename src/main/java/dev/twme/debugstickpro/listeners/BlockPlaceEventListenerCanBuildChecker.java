package dev.twme.debugstickpro.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.LinkedHashMap;
import java.util.UUID;

public class BlockPlaceEventListenerCanBuildChecker implements Listener {

    public static LinkedHashMap<Location, Boolean> locationCheck = new LinkedHashMap<>();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (locationCheck.containsKey(event.getBlock().getLocation())) {
            locationCheck.put(event.getBlock().getLocation(), event.isCancelled());
            event.setCancelled(true);
        }
    }

    public static boolean canBuild(Block block, UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);

        locationCheck.put(block.getLocation(), false);

        BlockPlaceEvent blockPlaceEvent = new BlockPlaceEvent(block, block.getState(), block, player.getInventory().getItemInMainHand(), player, true, EquipmentSlot.HAND);
        Bukkit.getPluginManager().callEvent(blockPlaceEvent);

        if (locationCheck.get(block.getLocation())) {
            locationCheck.remove(block.getLocation());
            return false;
        }
        locationCheck.remove(block.getLocation());
        return true;
    }
}
