package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.mode.freeze.FreezeBlockManager;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakEventListener implements Listener {
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        Location location = event.getBlock().getLocation();

        if (event.isCancelled()) {
            return;
        }

        if (FreezeBlockManager.isFreezeBlock(location)) {
            event.setCancelled(true);
            return;
        }
    }
}
