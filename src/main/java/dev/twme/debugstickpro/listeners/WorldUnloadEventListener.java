package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.FreezeBlockUtil.FreezeBlockManager;
import dev.twme.debugstickpro.util.PersistentKeys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldUnloadEvent;

public class WorldUnloadEventListener implements Listener {
    @EventHandler
    public void onWorldUnloadEvent(WorldUnloadEvent event) {
        event.getWorld().getEntities().forEach(entity -> {
            if (entity.getPersistentDataContainer().has(PersistentKeys.FREEZE_BLOCK_DISPLAY)) {
                FreezeBlockManager.removeOnChunkLoadOrUnload(entity);
            }
        });
    }
}
