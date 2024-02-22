package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.util.DebugStickItemCheck;
import dev.twme.debugstickpro.actionbar.CheckPlayerCanUseUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakEventListener implements Listener {
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!CheckPlayerCanUseUtil.check(player)) {
            return;
        }
        if (!DebugStickItemCheck.checkPlayer(player)) {
            return;
        }
        event.setCancelled(true);
    }
}
