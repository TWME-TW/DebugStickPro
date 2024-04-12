package dev.twme.debugstickpro.localization;

import dev.twme.debugstickpro.config.ConfigFile;
import dev.twme.debugstickpro.utils.Log;

import java.util.HashMap;
import java.util.Objects;

public class LangFileManager {

    /**
     * This is the map of the language file
     */
    private static final HashMap<String, LangFileReader> langFile = new HashMap<>();

    /**
     * Initialization of the language files
     */
    public static void initialization() {
        langFile.clear();
        LangFileReader.clearCache();
        for (String langFile : ConfigFile.Language.LangFiles) {
            addLang(langFile);
        }

        if (langFile.isEmpty()) {
            addLang(Objects.requireNonNullElse(ConfigFile.Language.DefaultLanguage, "en_US"));
        }
    }

    /**
     * Add a language file to the map
     *
     * @param locale the locale of the language file
     */
    public static void addLang(String locale) {

        if (langFile.containsKey(locale)) {
            return;
        }
        // TODO 更改載入邏輯
        LangFileReader lang;
        try {
            lang = new LangFileReader(locale);
        } catch (IllegalArgumentException e) {
            Log.warning(locale + ".yml not found");
            return;
        }

        if (!lang.getFile().exists()) {
            Log.warning(locale + ".yml not found");
            return;
        }
        Log.info(locale + ".yml loaded successfully");
        langFile.put(locale, lang);
    }

    /**
     * Get the language file
     *
     * @param locale the locale of the language file
     * @return the language file
     */
    public static LangFileReader getLang(String locale) {

        if (!langFile.containsKey(locale)) {
            if (langFile.containsKey(ConfigFile.Language.DefaultLanguage)) {
                return langFile.get(ConfigFile.Language.DefaultLanguage);
            }
            return langFile.get("en_US");
        }
        return langFile.get(locale);
    }
}
