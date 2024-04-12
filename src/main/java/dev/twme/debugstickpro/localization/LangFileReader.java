package dev.twme.debugstickpro.localization;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.utils.Log;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private static HashMap<String,String> cache = new HashMap<>();

    /**
     * Clear the cache of the language file
     */
    public static void clearCache() {
        cache.clear();
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

        langFileVersion = this.langFile.getInt(Lang.LangFileVersion);
        if (!checkLangFileVersion()) {
            return;
        }
    }

    /**
     * Check the version of the language file
     *
     * @return true if the version is compatible
     */
    public boolean checkLangFileVersion() {
        if (langFileVersion != DebugStickPro.LANG_VERSION) {
            Log.warning("Lang file version is not compatible with this version of the plugin.");
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String strDate = formatter.format(date);
            String backupFileName = file.getAbsolutePath().replace(locale, locale + strDate);

            File newFile = new File(backupFileName);
            if (file.renameTo(newFile)) {
                Log.warning("Old lang file has been backed up to " + newFile.getName());
            } else {
                Log.warning("Failed to backed up old lang file to " + newFile.getName());
            }
            return false;
        }
        return true;
    }

    /**
     * Get the string of the key
     *
     * @param key the key of the string
     * @return the string of the key
     */
    public String getString(String key) {

        if (cache.containsKey(locale + key)) {
            return cache.get(locale + key);
        }

        try {
            if (this.langFile.getString(key) == null) {
                set(key, LangFileManager.getLang("en_US").getString(key));
                Log.warning("Missing key: " + key + " in " + locale + ".yml");
                if (this.langFile.getString(key) == null) {
                    LangFileManager.getLang("en_US").set(key, "Missing...");
                    return "Missing key: \"" + key + "\" in en_US.yml";
                }
            }
        } catch (StackOverflowError e) {
            Log.warning(e.getMessage());
            return "Missing key: \"" + key + "\"" + " in " + locale + ".yml";
        }

        cache.put(locale + key, this.langFile.getString(key));
        return this.langFile.getString(key);
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
        save();
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
