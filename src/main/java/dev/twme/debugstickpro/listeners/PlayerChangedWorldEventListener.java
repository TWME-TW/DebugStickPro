package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.mode.freeze.FreezeBlockManager;
import dev.twme.debugstickpro.utils.PersistentKeys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerChangedWorldEventListener implements Listener {
    @EventHandler
    public void onPlayerChangedWorldEvent(PlayerChangedWorldEvent event) {
        event.getFrom().getEntities().forEach(entity -> {
            if (entity.getPersistentDataContainer().has(PersistentKeys.FREEZE_BLOCK_DISPLAY)) {
                FreezeBlockManager.removeOnChunkLoadOrUnload(entity);
            }
        });
    }
}
