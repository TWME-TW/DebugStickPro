package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.FreezeBlockUtil.FreezeBlockManager;
import dev.twme.debugstickpro.util.Log;
import dev.twme.debugstickpro.util.PersistentKeys;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class ChunkLoadEventListener implements Listener {
    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        Entity[] entities = event.getChunk().getEntities();
        for (Entity entity : entities) {
            if (entity.getPersistentDataContainer().has(PersistentKeys.FREEZE_BLOCK_DISPLAY)){
                FreezeBlockManager.removeOnChunkLoadOrUnload(entity);
            }
        }
    }
}
