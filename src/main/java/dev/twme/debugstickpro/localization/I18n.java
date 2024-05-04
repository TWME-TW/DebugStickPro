package dev.twme.debugstickpro.localization;

import dev.twme.debugstickpro.config.ConfigFile;

import java.util.List;
import java.util.UUID;

public class I18n {

    /**
     * Get the translated string of the key
     *
     * @param playerUUID the UUID of the player
     * @param key the key of the string
     * @return the string of the key
     */
    public static String string(UUID playerUUID, String key) {

        String playerLocale = PlayerLanguageManager.getLocale(playerUUID);
        LangFileReader lang = LangFileManager.getLang(playerLocale);

        return lang.getString(key);
    }

    /**
     * Get the translated string of the key (default language)
     *
     * @param key the key of the string
     * @return the string of the key
     */
    public static String string(String key) {

        LangFileReader langFileReader = LangFileManager.getLang(ConfigFile.Language.DefaultLanguage);

        return langFileReader.getString(key);
    }

    /**
     * Get the translated list of the key
     *
     * @param playerUUID the UUID of the player
     * @param key the key of the list
     * @return the list of the key
     */
    public static List<String> list(UUID playerUUID, String key) {

        String playerLocale = PlayerLanguageManager.getLocale(playerUUID);
        LangFileReader lang = LangFileManager.getLang(playerLocale);

        return lang.getList(key);
    }

    public static List<String> list(String key) {

        LangFileReader lang = LangFileManager.getLang(ConfigFile.Language.DefaultLanguage);

        return lang.getList(key);
    }
}
