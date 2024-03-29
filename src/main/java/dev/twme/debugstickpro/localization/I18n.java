package dev.twme.debugstickpro.localization;

import dev.twme.debugstickpro.config.ConfigFile;

import java.util.List;
import java.util.UUID;

public class I18n {
    public static String string(UUID playerUUID, String key) {

        String playerLocale = PlayerLanguageManager.getLocale(playerUUID);
        LangFileReader lang = LangFileManager.getLang(playerLocale);

        return lang.getString(key);
    }

    public static String string(String key) {

        LangFileReader langFileReader = LangFileManager.getLang(ConfigFile.Language.DefaultLanguage);

        return langFileReader.getString(key);
    }

    public static List<String> list(UUID playerUUID, String key) {

        String playerLocale = PlayerLanguageManager.getLocale(playerUUID);
        LangFileReader lang = LangFileManager.getLang(playerLocale);

        return lang.getList(key);
    }
}
