package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.util.PersistentKey;
import dev.twme.debugstickpro.util.actionbar.ActionBarTask;
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
        PersistentDataContainer playerHandItemDataNew = inventory.getItem(event.getNewSlot()).getItemMeta().getPersistentDataContainer();
        PersistentDataContainer playerHandItemDataOld = inventory.getItem(event.getPreviousSlot()).getItemMeta().getPersistentDataContainer();
        if (playerHandItemDataNew.has(PersistentKey.DEBUG_STICK_ITEM)) {
            ActionBarTask.playerList.add(player.getUniqueId());
        } else {
            ActionBarTask.playerList.remove(player.getUniqueId());
        }
    }
}
