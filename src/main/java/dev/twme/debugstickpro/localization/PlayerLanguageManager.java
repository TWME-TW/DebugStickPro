package dev.twme.debugstickpro.localization;

import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

public class PlayerLanguageManager {

    private static final HashMap<UUID, String> playerLang = new HashMap<>();
    private static final HashMap<String, NewLangFile> langFile = new HashMap<>();

    public static void addLang(String locale) {

        NewLangFile lang = new NewLangFile(locale);
        langFile.put(locale, lang);
    }

    public static NewLangFile getLang(String locale) {

        if (!langFile.containsKey(locale)) {

        }

        return langFile.get(locale);
    }

}
