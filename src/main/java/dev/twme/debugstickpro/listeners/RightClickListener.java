package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import dev.twme.debugstickpro.util.DebugStickItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class RightClickListener implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {

        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        Player player = event.getPlayer();

        if (!player.hasPermission("debugstickpro.use")) {
            return;
        }

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) {
            return;
        }

        if (!DebugStickItem.checkPlayer(player)) {
            return;
        }

        event.setCancelled(true);

        PlayerDataManager.playerRightClick(player.getUniqueId());
    }
}
