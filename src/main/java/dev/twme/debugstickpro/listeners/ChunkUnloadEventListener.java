package dev.twme.debugstickpro.listeners;


import dev.twme.debugstickpro.mode.freeze.FreezeBlockManager;
import dev.twme.debugstickpro.utils.PersistentKeys;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;

public class ChunkUnloadEventListener implements Listener {
    @EventHandler
    public void onChunkUnload(ChunkUnloadEvent event) {

        Entity[] entities = event.getChunk().getEntities();
        for (Entity entity : entities) {
            if (entity.getPersistentDataContainer().has(PersistentKeys.FREEZE_BLOCK_DISPLAY)){
                FreezeBlockManager.removeOnChunkLoadOrUnload(entity);
            }
        }
    }
}
