package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.mode.freeze.FreezeBlockManager;
import dev.twme.debugstickpro.playerdata.NewPlayerDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import java.util.UUID;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event){
        UUID uuid = event.getPlayer().getUniqueId();
        NewPlayerDataManager.removePlayerFromDisplayList(uuid);
        NewPlayerDataManager.removePlayerData(uuid);
        FreezeBlockManager.removeAllBlock(uuid);
    }
}
