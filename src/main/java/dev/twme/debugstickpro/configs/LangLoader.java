package dev.twme.debugstickpro.configs;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.util.Log;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LangLoader {
    private final static LangLoader instance = new LangLoader();
    private File file;
    private YamlConfiguration langFile;
    private LangLoader() {
    }

    public void load(){
        file = new File(DebugStickPro.getInstance().getDataFolder(), "lang.yml");

        if (!file.exists()) {
            DebugStickPro.getInstance().saveResource("lang.yml", false);
        }

        langFile = new YamlConfiguration();
        langFile.options().parseComments(true);

        try {
            langFile.load(file);
        } catch (Exception e) {
            Log.warning(e.getMessage());
        }

        loadValues();
    }

    private void loadValues() {

    }

    public void save() {
        try {
            langFile.save(file);
        } catch (Exception e) {
            Log.warning(e.getMessage());
        }
    }

    public void set(String path, Object value) {
        langFile.set(path, value);
        save();
        loadValues();
    }

    public static LangLoader getInstance() {
        return instance;
    }
}
