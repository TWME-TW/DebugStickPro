package dev.twme.debugstickpro.localization;

import dev.twme.debugstickpro.config.ConfigFile;
import dev.twme.debugstickpro.util.Log;

import java.util.HashMap;

public class LangFileManager {
    private static final HashMap<String, LangFileReader> langFile = new HashMap<>();

    public static void initialization() {
        langFile.clear();
        for (String langFile : ConfigFile.LangFiles) {
            addLang(langFile);
        }
    }

    public static void addLang(String locale) {

        if (langFile.containsKey(locale)) {
            return;
        }
        // TODO 更改載入邏輯
        LangFileReader lang;
        try {
            lang = new LangFileReader(locale);
        } catch (IllegalArgumentException e) {
            return;
        }

        if (!lang.getFile().exists()) {
            Log.warning(locale + ".yml not found");
            return;
        }
        Log.info(locale + ".yml loaded successfully");
        langFile.put(locale, lang);
    }

    public static LangFileReader getLang(String locale) {

        if (!langFile.containsKey(locale)) {
            if (langFile.containsKey(ConfigFile.DefaultLanguage)) {
                return langFile.get(ConfigFile.DefaultLanguage);
            }
            return langFile.get("en_US");
        }
        return langFile.get(locale);
    }
}
