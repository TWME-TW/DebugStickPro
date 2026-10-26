package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.mode.freeze.FreezePersistence;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;

public class WorldLoadEventListener implements Listener {
    @EventHandler
    public void onWorldLoadEvent(WorldLoadEvent event) {
        // Restore anchors left by an unexpected shutdown in worlds loaded after startup.
        FreezePersistence.recoverWorld(event.getWorld());
    }
}
