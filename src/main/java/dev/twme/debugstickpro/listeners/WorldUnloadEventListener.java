package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.mode.freeze.NewFreezeBlockManager;
import dev.twme.debugstickpro.util.PersistentKeys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldUnloadEvent;

public class WorldUnloadEventListener implements Listener {
    @EventHandler
    public void onWorldUnloadEvent(WorldUnloadEvent event) {
        event.getWorld().getEntities().forEach(entity -> {
            if (entity.getPersistentDataContainer().has(PersistentKeys.FREEZE_BLOCK_DISPLAY)) {
                NewFreezeBlockManager.clearFreezeBlocksOnWorld(event.getWorld().getUID());
            }
        });
    }
}
