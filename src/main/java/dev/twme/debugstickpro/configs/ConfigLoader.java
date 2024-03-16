package dev.twme.debugstickpro.configs;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.util.Log;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashSet;

public class ConfigLoader {
    private final static ConfigLoader instance = new ConfigLoader();
    private File file;
    private YamlConfiguration config;

    private ConfigLoader() {
    }

    public void load() {
        file = new File(DebugStickPro.getInstance().getDataFolder(), "config.yml");

        if (!file.exists()) {
            DebugStickPro.getInstance().saveResource("config.yml", false);
        }

        config = new YamlConfiguration();
        config.options().parseComments(true);

        try {
            config.load(file);
        } catch (Exception e) {
            Log.warning(e.getMessage());
        }

        // TODO: 將所有 config.yml 的值在這邊設置。
        loadValues();
    }

    private void loadValues() {
        ConfigFile.ConfigVersion = config.getInt("ConfigVersion");

        ConfigFile.ActionBarDisplay.AutoToCenter = config.getBoolean("ActionBarDisplay.AutoToCenter");
        ConfigFile.ActionBarDisplay.UpdateInterval = config.getLong("ActionBarDisplay.UpdateInterval");

        ConfigFile.CustomModelData.Enabled = config.getBoolean("CustomModelData.Enabled");
        ConfigFile.CustomModelData.CustomModelData = config.getInt("CustomModelData.CustomModelData");

        ConfigFile.WhitelistWorlds.Enabled = config.getBoolean("WhitelistWorlds.Enabled");
        ConfigFile.WhitelistWorlds.Worlds = new HashSet<>(config.getStringList("WhitelistWorlds.Worlds"));

        ConfigFile.BlacklistWorlds.Enabled = config.getBoolean("BlacklistWorlds.Enabled");
        ConfigFile.BlacklistWorlds.Worlds = new HashSet<>(config.getStringList("BlacklistWorlds.Worlds"));

        ConfigFile.AutoRegionProtection.Enabled = config.getBoolean("AutoRegionProtection.Enabled");
    }

    public void save() {
        try {
            config.save(file);
        } catch (Exception e) {
            Log.warning(e.getMessage());
        }
    }

    public void set(String path, Object value) {
        config.set(path, value);
        save();
        loadValues();
    }

    public static ConfigLoader getInstance() {
        return instance;
    }
}
