package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import dev.twme.debugstickpro.utils.DebugStickItem;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LeftClickListener implements Listener {
    @EventHandler
    public void onLeftClickEvent(PlayerInteractEvent event) {

        if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) {
            return;
        }

        Player player = event.getPlayer();

        if (!player.hasPermission("debugstickpro.use")) {
            return;
        }

        if (!DebugStickItem.checkPlayer(player)) {
            return;
        }

        Block targetBlock = event.getClickedBlock();
        if (targetBlock == null) {
            targetBlock = player.getTargetBlockExact(5);
        }

        event.setCancelled(true);

        PlayerDataManager.playerLeftClick(player.getUniqueId(), targetBlock);

    }
}
