package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.mode.freeze.NewFreezeBlockManager;
import dev.twme.debugstickpro.util.PersistentKeys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerChangedWorldEventListener implements Listener {
    @EventHandler
    public void onPlayerChangedWorldEvent(PlayerChangedWorldEvent event) {
        event.getFrom().getEntities().forEach(entity -> {
            if (entity.getPersistentDataContainer().has(PersistentKeys.FREEZE_BLOCK_DISPLAY)) {
                NewFreezeBlockManager.clearPlayerFreezeBlock(entity.getUniqueId());
            }
        });
    }
}
