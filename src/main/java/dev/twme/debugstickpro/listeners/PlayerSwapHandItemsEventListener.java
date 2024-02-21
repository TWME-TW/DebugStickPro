package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.util.DebugStickItemCheck;
import dev.twme.debugstickpro.util.Log;
import dev.twme.debugstickpro.util.actionbar.CheckPlayerCanUseUtil;
import dev.twme.debugstickpro.util.player.playerdata.DebugStickMode;
import dev.twme.debugstickpro.util.player.playerdata.PlayerData;
import dev.twme.debugstickpro.util.player.playerdata.PlayerDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedMainHandEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerSwapHandItemsEventListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPlayerSwapHandItemsEvent (PlayerSwapHandItemsEvent event) {
        if (!CheckPlayerCanUseUtil.check(event.getPlayer(), false)) {
            return;
        }
        if (!DebugStickItemCheck.isDebugStickItem(event.getOffHandItem())){
            return;
        }
        event.setCancelled(true);
        PlayerData playerData = PlayerDataManager.getPlayerData(event.getPlayer().getUniqueId());
        if (playerData.getPlayerMode() != DebugStickMode.ModeChange) {
            playerData.setPlayerMode(DebugStickMode.ModeChange);
        } else {
            playerData.setPlayerMode(playerData.getOldPlayerMode());
        }
    }

}