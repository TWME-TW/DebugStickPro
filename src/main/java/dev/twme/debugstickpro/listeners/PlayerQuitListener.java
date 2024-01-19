package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.util.actionbar.ActionbarUtil;
import dev.twme.debugstickpro.util.blockutil.BlockUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event){
        BlockUtil.remove(event.getPlayer().getUniqueId());
    }
}
