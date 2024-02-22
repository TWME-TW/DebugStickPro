package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.FreezeBlockUtil.FreezeBlockManager;
import dev.twme.debugstickpro.util.DebugStickItemCheck;
import dev.twme.debugstickpro.actionbar.CheckPlayerCanUseUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakEventListener implements Listener {
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlock().getLocation();
        if (FreezeBlockManager.isFreezeBlock(location)) {
            event.setCancelled(true);
            return;
        }
        if (!CheckPlayerCanUseUtil.check(player)) {
            return;
        }
        if (!DebugStickItemCheck.checkPlayer(player)) {
            return;
        }
        event.setCancelled(true);
    }
}
