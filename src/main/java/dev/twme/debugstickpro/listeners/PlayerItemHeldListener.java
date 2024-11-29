package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import dev.twme.debugstickpro.utils.CustomModelDataManager;
import dev.twme.debugstickpro.utils.DebugStickItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class PlayerItemHeldListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPlayerItemHeldEvent(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("debugstickpro.use")) {
            return;
        }

        Inventory inventory = player.getInventory();
        ItemStack newItem = inventory.getItem(event.getNewSlot());
        ItemStack oldItem = inventory.getItem(event.getPreviousSlot());
        UUID playerUUID = player.getUniqueId();

        if (DebugStickItem.isDebugStickItem(newItem)) {
            CustomModelDataManager.updatePlayerMode(player);
            PlayerDataManager.addPlayerToDisplayList(playerUUID);
        } else if (DebugStickItem.isDebugStickItem(oldItem)) {
            PlayerDataManager.removePlayerFromDisplayList(playerUUID);
        } else {
            PlayerDataManager.removePlayerFromDisplayList(playerUUID);
        }
    }
}
