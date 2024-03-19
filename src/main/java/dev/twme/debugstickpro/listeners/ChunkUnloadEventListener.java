package dev.twme.debugstickpro.listeners;


import dev.twme.debugstickpro.mode.freeze.NewFreezeBlockManager;
import dev.twme.debugstickpro.util.PersistentKeys;
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
                NewFreezeBlockManager.removeOnChunk(entity);
            }
        }
    }
}
