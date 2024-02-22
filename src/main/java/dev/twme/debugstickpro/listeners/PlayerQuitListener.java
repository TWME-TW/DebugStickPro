package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.FreezeBlockUtil.FreezeBlockManager;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
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
        PlayerDataManager.removePlayerEnableDisplay(uuid);
        FreezeBlockManager.removeAllBlock(uuid);
    }
}
