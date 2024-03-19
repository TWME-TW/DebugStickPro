package dev.twme.debugstickpro.mode.freeze;

import java.util.UUID;

public class FreezeLeftClick {
    public static void onLeftClick(UUID playerUUID) {
        FreezeBlockManager.removeAllPlayerFrozenBlock(playerUUID);
    }
}
