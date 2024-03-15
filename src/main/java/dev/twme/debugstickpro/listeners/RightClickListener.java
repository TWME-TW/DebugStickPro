package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.playerdata.NewPlayerDataManager;
import dev.twme.debugstickpro.util.DebugStickItemCheck;
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

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) {
            return;
        }

        if (!DebugStickItemCheck.checkPlayer(player)) {
            return;
        }

        if (!player.hasPermission("debugstickpro.use")) {
            return;
        }

        event.setCancelled(true);

        NewPlayerDataManager.playerRightClick(player.getUniqueId());
    }
}
