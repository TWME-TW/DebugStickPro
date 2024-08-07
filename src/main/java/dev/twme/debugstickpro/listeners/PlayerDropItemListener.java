package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import dev.twme.debugstickpro.utils.DebugStickItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener implements Listener {
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        DebugStickItem.checkPlayer(player);
        if (!DebugStickItem.checkPlayer(player)) {
            PlayerDataManager.removePlayerFromDisplayList(player.getUniqueId());
        }
    }
}
