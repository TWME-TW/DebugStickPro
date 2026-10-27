package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.mode.freeze.FreezeBlockManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldUnloadEvent;

public class WorldUnloadEventListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onWorldUnloadEvent(WorldUnloadEvent event) {
        // Restore the barrier anchors before the world is saved.
        FreezeBlockManager.removeAllInWorld(event.getWorld());
    }
}
