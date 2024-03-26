package dev.twme.debugstickpro.localization;

import java.util.List;
import java.util.UUID;

public class I18n {
    public static String str(UUID playerUUID, String key) {

        String playerLocale = PlayerLanguageManager.getLocale(playerUUID);
        LangFileReader lang = LangFileManager.getLang(playerLocale);

        return lang.getString(key);
    }

    public static String str(String key) {

        LangFileReader langFileReader = LangFileManager.getLang("en_US");

        return langFileReader.getString(key);
    }

    public static List<String> list(UUID playerUUID, String key) {

        String playerLocale = PlayerLanguageManager.getLocale(playerUUID);
        LangFileReader lang = LangFileManager.getLang(playerLocale);

        return lang.getList(key);
    }
}
