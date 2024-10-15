package dev.twme.debugstickpro.mode.freeze;

import dev.twme.debugstickpro.events.FreezeModeUnFreezeAllBlockEvent;
import org.bukkit.Bukkit;

import java.util.UUID;

public class FreezeLeftClick {
    public static void onLeftClick(UUID playerUUID) {
        FreezeModeUnFreezeAllBlockEvent event = new FreezeModeUnFreezeAllBlockEvent(playerUUID, FreezeBlockManager.getFreezeBlockCount(playerUUID));
        Bukkit.getPluginManager().callEvent(event);

        if (event.isCancelled()) {
            return;
        }

        FreezeBlockManager.removeAllPlayerFrozenBlock(playerUUID);
    }
}
