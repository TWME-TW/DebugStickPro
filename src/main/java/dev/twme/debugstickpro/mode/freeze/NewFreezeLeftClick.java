package dev.twme.debugstickpro.mode.freeze;

import java.util.UUID;

public class NewFreezeLeftClick {
    public static void onLeftClick(UUID playerUUID) {
        NewFreezeBlockManager.clearPlayerFreezeBlock(playerUUID);
    }
}
