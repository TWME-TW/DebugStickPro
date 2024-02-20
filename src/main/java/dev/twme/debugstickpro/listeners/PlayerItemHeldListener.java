package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.util.DebugStickItemCheck;
import dev.twme.debugstickpro.util.actionbar.TargetBlockTask;
import dev.twme.debugstickpro.util.actionbar.ActionbarUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerItemHeldListener implements Listener {
    @EventHandler
    public void onPlayerItemHeloEvent(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("debugstickpro.use")) {
            return;
        }

        Inventory inventory = player.getInventory();
        ItemStack newItem = inventory.getItem(event.getNewSlot());
        ItemStack oldItem = inventory.getItem(event.getPreviousSlot());
        if (DebugStickItemCheck.isDebugStickItem(newItem)) {
            TargetBlockTask.playerList.add(player.getUniqueId());
        } else if (DebugStickItemCheck.isDebugStickItem(oldItem)) {
            ActionbarUtil.removeActionBar(player.getUniqueId());
        } else if (newItem == null || oldItem == null) {
            ActionbarUtil.removeActionBar(player.getUniqueId());
        } else {
            ActionbarUtil.removeActionBar(player.getUniqueId());
        }
    }
}
