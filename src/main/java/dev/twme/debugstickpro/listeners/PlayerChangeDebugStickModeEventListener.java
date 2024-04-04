package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.config.ConfigFile;
import dev.twme.debugstickpro.events.PlayerChangeDebugStickModeEvent;
import dev.twme.debugstickpro.mode.freeze.FreezeBlockManager;
import dev.twme.debugstickpro.playerdata.DebugStickMode;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerChangeDebugStickModeEventListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerChangeDebugStickModeEvent(PlayerChangeDebugStickModeEvent event) {
        if (event.isCancelled()) {
            return;
        }

        UUID playerUUID = event.getPlayerUUID();

        if (ConfigFile.ModeSetting.ClassicMode.ClearSelectedDataTypeWhenModeChange) {
            if (event.getPreviousMode() == DebugStickMode.CLASSIC && !(event.getNewMode() == DebugStickMode.CLASSIC)) {
                PlayerDataManager.setPlayerData(playerUUID, PlayerDataManager.getPlayerData(playerUUID).setSelectedSubBlockDayaType(null));
            }
        }

        if (ConfigFile.ModeSetting.CopyMode.ClearStoredDataWhenModeChange) {
            if (event.getPreviousMode() == DebugStickMode.COPY && !(event.getNewMode() == DebugStickMode.COPY)) {
                PlayerDataManager.setPlayerData(playerUUID, PlayerDataManager.getPlayerData(playerUUID).setCopiedSubBlockData(new ArrayList<>()));
                PlayerDataManager.setPlayerData(playerUUID, PlayerDataManager.getPlayerData(playerUUID).removePlayerProfile());
            }
        }

        if (ConfigFile.ModeSetting.FreezeMode.UnfreezeAllBlockWhenModeChange) {
            if (event.getPreviousMode() == DebugStickMode.FREEZE && !(event.getNewMode() == DebugStickMode.FREEZE)) {
                FreezeBlockManager.removeAllPlayerFrozenBlock(playerUUID);
            }
        }
    }
}
