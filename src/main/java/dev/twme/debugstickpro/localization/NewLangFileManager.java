package dev.twme.debugstickpro.localization;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.configs.ConfigFile;

import java.util.HashMap;

public class NewLangFileManager {
    private static HashMap<String, NewLangFile> langFile;

    public static void initialization() {
        langFile = new HashMap<>();
        for (String langFile : ConfigFile.LangFiles) {
            addLang(langFile);
        }
    }

    public static void addLang(String locale) {

        if (langFile.containsKey(locale)) {
            return;
        }
        // TODO: 完成這邊(要把死完文件檔案讀取移至此處)
        NewLangFile newLangFile = new NewLangFile(locale);
        langFile.put(locale, newLangFile);
    }

    public static NewLangFile getLang(String locale) {

        if (!langFile.containsKey(locale)) {
            return langFile.get("en_US");
        }
        return langFile.get(locale);
    }
}
