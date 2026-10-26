package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.utils.PersistentKeys;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.persistence.PersistentDataType;

public class ChunkLoadEventListener implements Listener {
    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        removeLegacyFreezeDisplays(event.getChunk().getEntities());
    }

    /**
     * Older versions spawned real display entities for frozen blocks; they are never reused.
     */
    static void removeLegacyFreezeDisplays(Entity[] entities) {
        for (Entity entity : entities) {
            if (entity.getPersistentDataContainer().has(PersistentKeys.FREEZE_BLOCK_DISPLAY, PersistentDataType.STRING)) {
                entity.remove();
            }
        }
    }
}
