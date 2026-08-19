package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.localization.PlayerLanguageManager;
import dev.twme.debugstickpro.mode.freeze.FreezeBlockManager;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        PlayerDataManager.removePlayerFromDisplayList(uuid);
        PlayerDataManager.removePlayerData(uuid);
        PlayerLanguageManager.removePlayerLocale(uuid);
        FreezeBlockManager.removeAllPlayerFrozenBlock(uuid);
    }
}
