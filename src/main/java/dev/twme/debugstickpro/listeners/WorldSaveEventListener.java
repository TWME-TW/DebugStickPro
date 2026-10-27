package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.mode.freeze.FreezePersistence;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldSaveEvent;

public class WorldSaveEventListener implements Listener {
    @EventHandler
    public void onWorldSaveEvent(WorldSaveEvent event) {
        FreezePersistence.onWorldSaved(event.getWorld());
    }
}
