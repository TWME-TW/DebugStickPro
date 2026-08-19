package dev.twme.debugstickpro.localization;

import dev.twme.debugstickpro.config.ConfigFile;

import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerLanguageManager {

    /**
     * This is the map of the player language
     */
    private static final ConcurrentHashMap<UUID, String> playerLang = new ConcurrentHashMap<>();

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
        playerLang.put(playerUUID, normalizeLocale(locale));
    }

    public static void removePlayerLocale(UUID playerUUID) {
        playerLang.remove(playerUUID);
    }

    public static String normalizeLocale(String locale) {
        if (locale == null || locale.isBlank()) {
            return ConfigFile.Language.DefaultLanguage;
        }

        String[] parts = locale.replace('-', '_').split("_", 3);
        StringBuilder normalized = new StringBuilder(parts[0].toLowerCase(Locale.ROOT));
        if (parts.length >= 2 && !parts[1].isBlank()) {
            normalized.append('_').append(parts[1].toUpperCase(Locale.ROOT));
        }
        if (parts.length == 3 && !parts[2].isBlank()) {
            normalized.append('_').append(parts[2]);
        }
        return normalized.toString();
    }
}
