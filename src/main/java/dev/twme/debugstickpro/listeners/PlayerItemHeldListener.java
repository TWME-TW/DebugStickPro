package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.util.PersistentKey;
import dev.twme.debugstickpro.util.actionbar.ActionBarTask;
import dev.twme.debugstickpro.util.actionbar.ActionbarUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

public class PlayerItemHeldListener implements Listener {
    @EventHandler
    public void onPlayerItemHeloEvent(PlayerItemHeldEvent event){
        Player player = event.getPlayer();

        if (!player.hasPermission("debugstickpro.use")) {
            return;
        }

        Inventory inventory = player.getInventory();
        ItemStack newItem = inventory.getItem(event.getNewSlot());
        ItemStack oldItem = inventory.getItem(event.getPreviousSlot());
        if (newItem != null && newItem.getItemMeta() != null && newItem.getItemMeta().getPersistentDataContainer().has(PersistentKey.DEBUG_STICK_ITEM)) {
            ActionBarTask.playerList.add(player.getUniqueId());
        } else if (oldItem != null && oldItem.getItemMeta() != null && oldItem.getItemMeta().getPersistentDataContainer().has(PersistentKey.DEBUG_STICK_ITEM)){
            ActionbarUtil.removeActionBar(player.getUniqueId());
        } else if(newItem == null || oldItem == null){
            ActionbarUtil.removeActionBar(player.getUniqueId());
        }
    }
}
