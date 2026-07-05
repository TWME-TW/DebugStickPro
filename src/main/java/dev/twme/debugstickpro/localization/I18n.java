package dev.twme.debugstickpro.localization;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.config.ConfigFile;
import dev.twme.debugstickpro.hook.PlaceholderAPIUtil;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class I18n {

    /**
     * Get the translated string of the key
     *
     * @param playerUUID the UUID of the player
     * @param key        the key of the string
     * @return the string of the key
     */
    public static String string(UUID playerUUID, String key) {

        String playerLocale = PlayerLanguageManager.getLocale(playerUUID);
        LangFileReader lang = LangFileManager.getLang(playerLocale);

        return PlaceholderAPIUtil.insertPAPI(Bukkit.getPlayer(playerUUID), lang.getString(key));
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
     * @param key        the key of the list
     * @return the list of the key
     */
    public static List<String> list(UUID playerUUID, String key) {

        String playerLocale = PlayerLanguageManager.getLocale(playerUUID);
        LangFileReader lang = LangFileManager.getLang(playerLocale);

        return PlaceholderAPIUtil.insertPAPI(Bukkit.getPlayer(playerUUID), lang.getList(key));
    }

    public static List<String> list(String key) {

        LangFileReader lang = LangFileManager.getLang(ConfigFile.Language.DefaultLanguage);

        return lang.getList(key);
    }

    /**
     * Translate a SubBlockData value for the player.
     *
     * @param playerUUID the UUID of the player
     * @param subBlockData the SubBlockData whose value should be translated
     * @return translated value, or the raw value in lower case when no translation exists
     */
    public static String blockDataValue(UUID playerUUID, SubBlockData subBlockData) {
        String playerLocale = PlayerLanguageManager.getLocale(playerUUID);
        LangFileReader lang = LangFileManager.getLang(playerLocale);
        String rawValue = subBlockData.getDataAsString();

        String translated = findBlockDataValue(lang, subBlockData.name(), rawValue);
        if (translated != null) {
            return translated;
        }

        if (!playerLocale.equals(ConfigFile.Language.DefaultLanguage)) {
            translated = findBlockDataValue(LangFileManager.getLang(ConfigFile.Language.DefaultLanguage), subBlockData.name(), rawValue);
            if (translated != null) {
                return translated;
            }
        }

        return rawValue.toLowerCase(Locale.ROOT);
    }

    private static String findBlockDataValue(LangFileReader lang, String dataType, String rawValue) {
        String translated = lang.getOptionalString(Lang.DataValueName.value(dataType, rawValue));
        if (translated != null) {
            return translated;
        }

        return lang.getOptionalString(Lang.DataValueName.common(rawValue));
    }
}
