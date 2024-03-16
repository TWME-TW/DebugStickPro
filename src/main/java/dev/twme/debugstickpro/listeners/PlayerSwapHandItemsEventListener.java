package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import dev.twme.debugstickpro.util.DebugStickItem;
import dev.twme.debugstickpro.util.CheckPlayerCanUseUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerSwapHandItemsEventListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPlayerSwapHandItemsEvent (PlayerSwapHandItemsEvent event) {
        if (!CheckPlayerCanUseUtil.check(event.getPlayer(), false)) {
            return;
        }
        if (!DebugStickItem.isDebugStickItem(event.getOffHandItem())){
            return;
        }
        event.setCancelled(true);

        PlayerDataManager.nextDebugStickMode(event.getPlayer().getUniqueId());
    }
}
