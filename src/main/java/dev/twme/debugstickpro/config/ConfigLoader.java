package dev.twme.debugstickpro.config;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.util.Log;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

        ConfigFile.ConfigVersion = config.getInt("ConfigVersion");

        if (!checkConfigVersion()) {
            load();
            return;
        }

        loadValues();
    }

    private boolean checkConfigVersion() {
        if (ConfigFile.ConfigVersion != DebugStickPro.CONFIG_VERSION) {
            Log.warning("Config file version is not compatible with this version of the plugin.");
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String strDate = formatter.format(date);
            String backupFileName = file.getAbsolutePath().replace("config", "config-" + strDate);
            File newFile = new File(backupFileName);
            if (file.renameTo(newFile)) {
                Log.warning("Old config file has been backed up to " + newFile.getName());
            } else {
                Log.warning("Failed to backup old config file");
            }
            return false;
        }
        return true;
    }

    private void loadValues() {

        ConfigFile.Language.LangFiles.addAll(config.getStringList("Language.LangFiles"));
        ConfigFile.Language.DefaultLanguage = config.getString("Language.DefaultLanguage");

        ConfigFile.ActionBarDisplay.AutoToCenter = config.getBoolean("ActionBarDisplay.AutoToCenter");
        ConfigFile.ActionBarDisplay.UpdateInterval = config.getLong("ActionBarDisplay.UpdateInterval");

        ConfigFile.DebugStickItem.Material = Material.valueOf(config.getString("DebugStickItem.Material"));
        ConfigFile.DebugStickItem.DisplayName = config.getString("DebugStickItem.DisplayName");

        MiniMessage mm = MiniMessage.miniMessage();
        ArrayList<Component> lore = new ArrayList<>();
        for (String loreString : config.getStringList("DebugStickItem.Lore")) {
            lore.add(mm.deserialize(loreString));
        }
        ConfigFile.DebugStickItem.Lore = lore;
        ConfigFile.DebugStickItem.CustomModelData.Enabled = config.getBoolean("CustomModelData.DebugStickItem.Enabled");
        ConfigFile.DebugStickItem.CustomModelData.CustomModelData = config.getInt("CustomModelData.DebugStickItem.CustomModelData");

        ConfigFile.WhitelistWorlds.Enabled = config.getBoolean("WhitelistWorlds.Enabled");
        ConfigFile.WhitelistWorlds.Worlds = new HashSet<>(config.getStringList("WhitelistWorlds.Worlds"));

        ConfigFile.BlacklistWorlds.Enabled = config.getBoolean("BlacklistWorlds.Enabled");
        ConfigFile.BlacklistWorlds.Worlds = new HashSet<>(config.getStringList("BlacklistWorlds.Worlds"));

        ConfigFile.AutoRegionProtection.Enabled = config.getBoolean("AutoRegionProtection.Enabled");

        ConfigFile.BlockDataFilter.Whitelist.Enabled = config.getBoolean("BlockDataFilter.Whitelist.Enabled");
        ConfigFile.BlockDataFilter.Whitelist.Whitelist = new HashSet<>(config.getStringList("BlockDataFilter.Whitelist.Whitelist"));
        ConfigFile.BlockDataFilter.Blacklist.Enabled = config.getBoolean("BlockDataFilter.Blacklist.Enabled");
        ConfigFile.BlockDataFilter.Blacklist.Blacklist = new HashSet<>(config.getStringList("BlockDataFilter.Blacklist.Blacklist"));

        ConfigFile.ModeSetting.ClassicMode.ClearSelectedDataTypeWhenModeChange = config.getBoolean("ModeSetting.ClassicMode.ClearSelectedDataTypeWhenModeChange");
        ConfigFile.ModeSetting.CopyMode.ClearStoredDataWhenModeChange = config.getBoolean("ModeSetting.CopyMode.ClearStoredDataWhenModeChange");
        ConfigFile.ModeSetting.FreezeMode.UnfreezeAllBlockWhenModeChange = config.getBoolean("ModeSetting.FreezeMode.UnfreezeAllBlockWhenModeChange");

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
