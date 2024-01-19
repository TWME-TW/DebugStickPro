package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.util.PersistentKey;
import dev.twme.debugstickpro.util.actionbar.ActionBarTask;
import dev.twme.debugstickpro.util.actionbar.ActionbarUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if (!player.hasPermission("debugstickpro.use")) {
            return;
        }
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item != null && item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer().has(PersistentKey.DEBUG_STICK_ITEM)) {
            ActionBarTask.playerList.add(player.getUniqueId());
        }
    }
}
