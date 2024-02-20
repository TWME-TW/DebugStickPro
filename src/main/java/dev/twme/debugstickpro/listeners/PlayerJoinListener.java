package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.util.DebugStickItemCheck;
import dev.twme.debugstickpro.util.actionbar.TargetBlockTask;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if (!player.hasPermission("debugstickpro.use")) {
            return;
        }
        ItemStack item = player.getInventory().getItemInMainHand();

        if (DebugStickItemCheck.isDebugStickItem(item)) {
            TargetBlockTask.playerList.add(player.getUniqueId());
        }
    }
}
