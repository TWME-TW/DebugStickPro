package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.mode.freeze.NewFreezeBlockManager;
import dev.twme.debugstickpro.util.DebugStickItem;
import dev.twme.debugstickpro.util.CheckPlayerCanUseUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakEventListener implements Listener {
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {

        if (NewFreezeBlockManager.isFreezeBlock(event.getBlock())) {
            event.setCancelled(true);
            return;
        }
    }
}
