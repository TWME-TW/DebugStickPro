package dev.twme.debugstickpro.localization;

import java.util.UUID;

public class I18n {
    public static String str(UUID playerUUID, String key) {

        String playerLocale = PlayerLanguageManager.getLocale(playerUUID);
        NewLangFile lang = NewLangFileManager.getLang(playerLocale);

        return lang.getString(key);
    }
}
