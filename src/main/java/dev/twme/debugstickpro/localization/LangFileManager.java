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
        // TODO: 完成這邊(要把死完文件檔案讀取移至此處)
        LangFileReader lang;
        try {
            lang = new LangFileReader(locale);
        } catch (IllegalArgumentException e) {
            return;
        }

        if (!lang.getFile().exists()) {
            return;
        }
        Log.info(locale + ".yml added successfully");
        langFile.put(locale, lang);
    }

    public static LangFileReader getLang(String locale) {

        if (!langFile.containsKey(locale)) {
            return langFile.get("en_US");
        }
        return langFile.get(locale);
    }
}
