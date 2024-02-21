package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.util.DebugStickItemCheck;
import dev.twme.debugstickpro.util.player.playerdata.PlayerData;
import dev.twme.debugstickpro.util.player.playerdata.PlayerDataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class LeftClickListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onLeftClickEvent(PlayerInteractEvent event) {

        if (!event.getAction().isLeftClick()) {
            return;
        }

        Player player = event.getPlayer();
        if (!DebugStickItemCheck.checkPlayer(player)) {
            return;
        }

        event.setCancelled(true);
        PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());
        playerData.changeSelected();
    }
}
