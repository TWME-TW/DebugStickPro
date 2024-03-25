package dev.twme.debugstickpro.mode.freeze;

import dev.twme.debugstickpro.localization.I18n;
import dev.twme.debugstickpro.localization.Lang;

import java.util.UUID;

public class FreezeActionBarDisplay {
    public static String getDisplay(UUID playerUUID) {
        if (FreezeBlockManager.getFreezeBlockCount(playerUUID) == 0) {
            return I18n.str(playerUUID, Lang.Tips.freezeModeIntroduction);
        } else {
            return I18n.str(playerUUID, Lang.ActionBar.formatFreezeBlockCount(FreezeBlockManager.getFreezeBlockCount(playerUUID)));
        }
    }
}
