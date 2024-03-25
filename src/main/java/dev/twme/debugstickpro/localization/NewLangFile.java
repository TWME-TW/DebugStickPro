package dev.twme.debugstickpro.localization;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.util.Log;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewLangFile {
    private File file;
    private YamlConfiguration langFile;
    private int langFileVersion;
    private final String locale;

    public NewLangFile(String locale) {
        this.locale = locale;
        load();
    }

    public void load() {
        file = new File(DebugStickPro.getInstance().getDataFolder(), "lang/" + locale + ".yml");

        if (!file.exists()) {
            DebugStickPro.getInstance().saveResource("lang/" + locale + ".yml", false);
        }

        langFile = new YamlConfiguration();
        langFile.options().parseComments(true);

        try {
            langFile.load(file);
        } catch (Exception e) {
            Log.warning(e.getMessage());
        }

        langFileVersion = langFile.getInt(NewLang.LangFileVersion);

        if (!checkLangFileVersion()) {

            return;
        }
    }

    public boolean checkLangFileVersion() {
        if (langFileVersion != DebugStickPro.LANG_VERSION) {
            Log.warning("Lang file version is not compatible with this version of the plugin.");
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String strDate = formatter.format(date);
            String backupFileName = file.getAbsolutePath().replace("lang", "lang-" + strDate);

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

    public String getString(String key) {
        if (langFile.getString(key) == null) {
            String newLore = NewLangFileManager.getLang("en_US").getString(key);
            setString(key, newLore);

            return getString(key);
        }
        return langFile.getString(key);
    }

    public void setString(String key, String value) {
        langFile.set(key, value);
        save();
    }

    public void save() {
        try {
            langFile.save(file);
        } catch (Exception e) {
            Log.warning(e.getMessage());
        }
    }
}
