package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.localization.PlayerLanguageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLocaleChangeEvent;

public class PlayerLocaleChangeEventListener implements Listener {
    @EventHandler
    public void onPlayerLocaleChangeEvent(PlayerLocaleChangeEvent event) {
        Player player = event.getPlayer();
        PlayerLanguageManager.setPlayerLocale(player.getUniqueId(), player.locale().toString());
    }
}
