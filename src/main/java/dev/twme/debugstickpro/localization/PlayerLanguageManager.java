package dev.twme.debugstickpro.localization;

import dev.twme.debugstickpro.config.ConfigFile;

import java.util.HashMap;
import java.util.UUID;

public class PlayerLanguageManager {

    /**
     * This is the map of the player language
     */
    private static final HashMap<UUID, String> playerLang = new HashMap<>();

    /**
     * Get the locale of the player
     *
     * @param playerUUID the UUID of the player
     * @return the locale of the player
     */
    public static String getLocale(UUID playerUUID) {

        if (!playerLang.containsKey(playerUUID)) {
            return ConfigFile.Language.DefaultLanguage;
        }
        return playerLang.get(playerUUID);
    }

    /**
     * Set the locale of the player
     *
     * @param playerUUID the UUID of the player
     * @param locale the locale of the player
     */
    public static void setPlayerLocale(UUID playerUUID, String locale) {
        playerLang.put(playerUUID, locale);
    }
}
