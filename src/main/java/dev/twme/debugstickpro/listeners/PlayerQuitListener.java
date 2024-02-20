package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.util.actionbar.TargetBlockTask;
import dev.twme.debugstickpro.util.player.playerdata.PlayerDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event){
        UUID uuid = event.getPlayer().getUniqueId();
        PlayerDataManager.removePlayerData(uuid);
        // ActionBarTask.playerList.remove(event.getPlayer().getUniqueId());
        // 暫時替換為
        TargetBlockTask.removePlayer(uuid);
    }
}
