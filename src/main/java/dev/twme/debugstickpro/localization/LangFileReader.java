package dev.twme.debugstickpro.localization;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.util.Log;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LangFileReader {
    private File file;
    private YamlConfiguration langFile;
    private int langFileVersion;
    private final String locale;

    public LangFileReader(String locale) throws IllegalArgumentException {
        this.locale = locale;
        load();
    }

    public void load() throws IllegalArgumentException {
        file = new File(DebugStickPro.getInstance().getDataFolder(), "lang" + File.separator + locale + ".yml");

        if (!file.exists()) {
            DebugStickPro.getInstance().saveResource("lang" + File.separator + locale + ".yml", false);
            Log.info("lang" + File.separator + locale + ".yml has been created.");
        }

        this.langFile = new YamlConfiguration();
        this.langFile.options().parseComments(true);

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

    public String getString(String key) {

        if (this.langFile.getString(key) == null) {
            set(key, LangFileManager.getLang("en_US").getString(key));
            return this.langFile.getString(key);
        }
        return this.langFile.getString(key);
    }

    public List<String> getList(String key) {

        List<String> messages;

        if (this.langFile.getString(key) == null) {
            messages = LangFileManager.getLang("en_US").getList(key);
            set(key, messages);
            return messages;
        }
        messages = this.langFile.getStringList(key);
        return messages;
    }

    public void set(String path, Object value) {
        this.langFile.set(path, value);
        save();
    }

    public void save() {
        try {
            this.langFile.save(file);
        } catch (Exception e) {
            Log.warning(e.getMessage());
        }
    }

    public File getFile() {
        return file;
    }
}
