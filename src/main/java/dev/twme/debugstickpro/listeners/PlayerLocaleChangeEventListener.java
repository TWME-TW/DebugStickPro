package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.localization.PlayerLanguageManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLocaleChangeEvent;

public class PlayerLocaleChangeEventListener implements Listener {
    @EventHandler
    public void onPlayerLocaleChangeEvent(PlayerLocaleChangeEvent event) {
        PlayerLanguageManager.setPlayerLocale(event.getPlayer().getUniqueId(), event.getLocale());
    }
}
