package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.ConfigFile;
import dev.twme.debugstickpro.events.PlayerChangeDebugStickModeEvent;
import dev.twme.debugstickpro.mode.freeze.NewFreezeBlockManager;
import dev.twme.debugstickpro.playerdata.DebugStickMode;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class PlayerChangeDebugStickModeEventListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerChangeDebugStickModeEvent(PlayerChangeDebugStickModeEvent event) {
        if (event.isCancelled()) {
            return;
        }

        if (ConfigFile.ModeSetting.ClassicMode.ClearSelectedDataTypeWhenModeChange) {
            if (event.getPreviousMode() == DebugStickMode.Classic && !(event.getNewMode() == DebugStickMode.Classic)) {
                PlayerDataManager.setPlayerData(event.getPlayerUUID(), PlayerDataManager.getPlayerData(event.getPlayerUUID()).setSelectedSubBlockDayaType(null));
            }
        }

        if (ConfigFile.ModeSetting.CopyMode.ClearStoredDataWhenModeChange) {
            if (event.getPreviousMode() == DebugStickMode.Copy && !(event.getNewMode() == DebugStickMode.Copy)) {
                PlayerDataManager.setPlayerData(event.getPlayerUUID(), PlayerDataManager.getPlayerData(event.getPlayerUUID()).setCopiedSubBlockData(new ArrayList<>()));
            }
        }

        if (ConfigFile.ModeSetting.FreezeMode.UnfreezeAllBlockWhenModeChange) {
            if (event.getPreviousMode() == DebugStickMode.Freeze && !(event.getNewMode() == DebugStickMode.Freeze)) {
                NewFreezeBlockManager.clearPlayerFreezeBlock(event.getPlayerUUID());
            }
        }
    }
}
