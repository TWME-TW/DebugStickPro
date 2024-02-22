package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.util.DebugStickItemCheck;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerItemHeldListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPlayerItemHeloEvent(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("debugstickpro.use")) {
            return;
        }

        Inventory inventory = player.getInventory();
        ItemStack newItem = inventory.getItem(event.getNewSlot());
        ItemStack oldItem = inventory.getItem(event.getPreviousSlot());

        if (DebugStickItemCheck.isDebugStickItem(newItem)) {

            PlayerDataManager.addPlayerEnableDisplay(player.getUniqueId());

        } else if (DebugStickItemCheck.isDebugStickItem(oldItem)) {

            PlayerDataManager.removePlayerEnableDisplay(player.getUniqueId());

        } else if (newItem == null || oldItem == null) {

            PlayerDataManager.removePlayerEnableDisplay(player.getUniqueId());
        } else {
            PlayerDataManager.removePlayerEnableDisplay(player.getUniqueId());
        }
    }
}
