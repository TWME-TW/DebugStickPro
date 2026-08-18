package dev.twme.debugstickpro.localization;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.utils.Log;
import dev.twme.debugstickpro.utils.YamlDefaultMerger;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class LangFileReader {
    private File file;
    private YamlConfiguration langFile;
    private int langFileVersion;
    private final String locale;


    // cache for lang file

    /**
     * This is the cache of the language file
     */
    private static final HashMap<String, String> cache = new HashMap<>();
    private static final HashMap<String, String> optionalStringCache = new HashMap<>();

    /**
     * Clear the cache of the language file
     */
    public static void clearCache() {
        cache.clear();
        optionalStringCache.clear();
    }

    /**
     * This is the constructor of the LangFileReader
     *
     * @param locale the locale of the language file
     */
    public LangFileReader(String locale) throws IllegalArgumentException {
        this.locale = locale;
        load();
    }

    /**
     * Load the language file
     */
    public void load() throws IllegalArgumentException {
        file = new File(DebugStickPro.getInstance().getDataFolder(), "lang" + File.separator + locale + ".yml");

        if (!file.exists()) {
            DebugStickPro.getInstance().saveResource("lang" + File.separator + locale + ".yml", false);
            Log.info("lang" + File.separator + locale + ".yml has been created.");
        }

        langFile = new YamlConfiguration();
        langFile.options().parseComments(true);

        try {
            this.langFile.load(file);
        } catch (Exception e) {
            Log.warning(e.getMessage());
        }

        YamlConfiguration defaults = loadDefaults();
        boolean changed = defaults != null && YamlDefaultMerger.addMissingValues(this.langFile, defaults);
        if (this.langFile.getInt(Lang.LangFileVersion) < DebugStickPro.LANG_VERSION) {
            this.langFile.set(Lang.LangFileVersion, DebugStickPro.LANG_VERSION);
            changed = true;
        } else if (this.langFile.getInt(Lang.LangFileVersion) > DebugStickPro.LANG_VERSION) {
            Log.warning(locale + ".yml is newer than this version of the plugin.");
        }
        langFileVersion = this.langFile.getInt(Lang.LangFileVersion);

        if (changed) {
            Log.info("Updated " + locale + ".yml with missing default values.");
            save();
        }
    }

    private YamlConfiguration loadDefaults() {
        String resourcePath = "lang/" + locale + ".yml";
        InputStream stream = DebugStickPro.getInstance().getResource(resourcePath);
        if (stream == null && !"en_US".equals(locale)) {
            stream = DebugStickPro.getInstance().getResource("lang/en_US.yml");
        }
        if (stream == null) {
            return null;
        }

        try (InputStream defaultsStream = stream) {
            YamlConfiguration defaults = new YamlConfiguration();
            defaults.options().parseComments(true);
            defaults.load(new InputStreamReader(defaultsStream, StandardCharsets.UTF_8));
            return defaults;
        } catch (Exception e) {
            Log.warning("Failed to load bundled defaults for " + locale + ".yml: " + e.getMessage());
            return null;
        }
    }

    /**
     * Check the version of the language file
     *
     * @return true if the version is compatible
     */
    public boolean checkLangFileVersion() {
        return langFileVersion == DebugStickPro.LANG_VERSION;
    }

    /**
     * Get the string of the key
     *
     * @param key the key of the string
     * @return the string of the key
     */
    public String getString(String key) {

        String cacheKey = cacheKey(key);
        if (cache.containsKey(cacheKey)) {
            return cache.get(cacheKey);
        }

        String value;
        try {
            value = this.langFile.getString(key);
            if (value == null) {
                set(key, LangFileManager.getLang("en_US").getString(key));
                Log.warning("Missing key: " + key + " in " + locale + ".yml");
                value = this.langFile.getString(key);
                if (value == null) {
                    LangFileManager.getLang("en_US").set(key, "Missing...");
                    return "Missing key: \"" + key + "\" in en_US.yml";
                }
            }
        } catch (StackOverflowError e) {
            Log.warning(e.getMessage());
            return "Missing key: \"" + key + "\"" + " in " + locale + ".yml";
        }

        cache.put(cacheKey, value);
        return value;
    }

    /**
     * Get a string without falling back to the default language or modifying the file.
     *
     * @param key the key of the string
     * @return the string of the key, or null if it is not configured
     */
    public String getOptionalString(String key) {
        String cacheKey = cacheKey(key);
        if (optionalStringCache.containsKey(cacheKey)) {
            return optionalStringCache.get(cacheKey);
        }

        String value = this.langFile.getString(key);
        optionalStringCache.put(cacheKey, value);
        return value;
    }

    /**
     * Get the list of the key
     *
     * @param key the key of the list
     * @return the list of the key
     */
    public List<String> getList(String key) {

        List<String> messages;
        try {
            if (this.langFile.getList(key) == null) {
                set(key, LangFileManager.getLang("en_US").getList(key));
                Log.warning("Missing key: " + key + " in " + locale + ".yml");
                if (this.langFile.getList(key) == null) {
                    LangFileManager.getLang("en_US").set(key, "Missing...");
                    return List.of("Missing key: \"" + key + "\" in en_US.yml");
                }
            }
        } catch (StackOverflowError e) {
            return List.of("Missing key: \"" + key + "\"" + " in " + locale + ".yml");
        }
        messages = this.langFile.getStringList(key);
        return messages;
    }

    /**
     * Set the value of the path
     *
     * @param path the path of the value
     * @param value the value of the path
     */
    public void set(String path, Object value) {
        this.langFile.set(path, value);
        cache.remove(cacheKey(path));
        optionalStringCache.remove(cacheKey(path));
        save();
    }

    private String cacheKey(String key) {
        return locale + ":" + key;
    }

    /**
     * Save the language file
     */
    public void save() {
        try {
            this.langFile.save(file);
        } catch (Exception e) {
            Log.warning(e.getMessage());
        }
    }

    /**
     * Get the locale of the language file
     *
     * @return the locale of the language file
     */
    public File getFile() {
        return file;
    }
}
