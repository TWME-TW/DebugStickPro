package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.mode.freeze.FreezeBlockManager;
import dev.twme.debugstickpro.utils.PersistentKeys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldUnloadEvent;

public class WorldUnloadEventListener implements Listener {
    @EventHandler
    public void onWorldUnloadEvent(WorldUnloadEvent event) {
        event.getWorld().getEntities().forEach(entity -> {
            if (entity.getPersistentDataContainer().has(PersistentKeys.FREEZE_BLOCK_DISPLAY, org.bukkit.persistence.PersistentDataType.STRING)) {
                FreezeBlockManager.removeOnChunkLoadOrUnload(entity);
            }
        });
    }
}
