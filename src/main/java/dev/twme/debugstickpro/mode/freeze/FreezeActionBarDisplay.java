package dev.twme.debugstickpro.mode.freeze;

import dev.twme.debugstickpro.localization.LangFile;

import java.util.UUID;

public class FreezeActionBarDisplay {
    public static String getDisplay(UUID playerUUID) {
        if (FreezeBlockManager.getFreezeBlockCount(playerUUID) == 0) {
            return LangFile.Tips.freezeModeIntroduction;
        } else {
            return LangFile.ActionBar.formatFreezeBlockCount(FreezeBlockManager.getFreezeBlockCount(playerUUID));
        }
    }
}
