package dev.twme.debugstickpro.mode.freeze;

import dev.twme.debugstickpro.localization.I18n;
import dev.twme.debugstickpro.localization.Lang;

import java.util.UUID;

public class FreezeActionBarDisplay {
    public static String getDisplay(UUID playerUUID) {
        if (FreezeBlockManager.getFreezeBlockCount(playerUUID) == 0) {
            return I18n.string(playerUUID, Lang.Tips.freezeModeIntroduction);
        } else {
            return Lang.ActionBar.formatFreezeBlockCount(I18n.string(playerUUID, Lang.ActionBar.FreezeBlockCount), FreezeBlockManager.getFreezeBlockCount(playerUUID));
        }
    }
}
