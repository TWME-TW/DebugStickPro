package dev.twme.debugstickpro.localization;

import dev.twme.debugstickpro.util.Log;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLocaleChangeEvent;

import java.util.Locale;

public class PlayerLocaleChangeEventListener implements Listener {
    @EventHandler
    public void onPlayerLocaleChange(PlayerLocaleChangeEvent event) {
        Log.announcement("Player " + event.getPlayer().getName() + " changed locale to " + event.locale() + "!");

    }
}
