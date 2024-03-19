package dev.twme.debugstickpro.mode.freeze;

import dev.twme.debugstickpro.configs.LangFile;

import java.util.UUID;

public class FreezeActionBarDisplay {
    public static String getDisplay(UUID playerUUID) {
        if (NewFreezeBlockManager.getFreezeBlockCount(playerUUID) == 0) {
            return LangFile.Tips.freezeModeIntroduction;
        } else {
            return LangFile.ActionBar.formatFreezeBlockCount(NewFreezeBlockManager.getFreezeBlockCount(playerUUID));
        }
    }
}
