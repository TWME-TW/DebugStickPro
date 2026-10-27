package dev.twme.debugstickpro.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;

public class ChunkUnloadEventListener implements Listener {
    @EventHandler
    public void onChunkUnload(ChunkUnloadEvent event) {
        ChunkLoadEventListener.removeLegacyFreezeDisplays(event.getChunk().getEntities());
    }
}
