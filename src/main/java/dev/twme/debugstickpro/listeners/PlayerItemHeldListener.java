package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import dev.twme.debugstickpro.util.DebugStickItemCheck;
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

        if (DebugStickItemCheck.isDebugStickItem(newItem)) {
            PlayerDataManager.addPlayerToDisplayList(playerUUID);
        } else if (DebugStickItemCheck.isDebugStickItem(oldItem)) {
            PlayerDataManager.removePlayerFromDisplayList(playerUUID);
        } else {
            PlayerDataManager.removePlayerFromDisplayList(playerUUID);
        }
    }
}
