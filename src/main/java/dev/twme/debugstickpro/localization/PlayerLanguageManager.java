package dev.twme.debugstickpro.localization;

import dev.twme.debugstickpro.config.ConfigFile;

import java.util.HashMap;
import java.util.UUID;

public class PlayerLanguageManager {
    private static final HashMap<UUID, String> playerLang = new HashMap<>();
    public static String getLocale(UUID playerUUID) {

        if (!playerLang.containsKey(playerUUID)) {
            return ConfigFile.DefaultLanguage;
        }
        return playerLang.get(playerUUID);
    }

    public static void setPlayerLocale(UUID playerUUID, String locale) {
        playerLang.put(playerUUID, locale);
    }
}
