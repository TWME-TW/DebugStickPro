package dev.twme.debugstickpro.mode.freeze;

import dev.twme.debugstickpro.events.UnFreezeAllBlockEvent;
import org.bukkit.Bukkit;

import java.util.UUID;

public class FreezeLeftClick {
    public static void onLeftClick(UUID playerUUID) {
        UnFreezeAllBlockEvent event = new UnFreezeAllBlockEvent(playerUUID, FreezeBlockManager.getFreezeBlockCount(playerUUID));
        Bukkit.getPluginManager().callEvent(event);

        if (event.isCancelled()) {
            return;
        }

        FreezeBlockManager.removeAllPlayerFrozenBlock(playerUUID);
    }
}
