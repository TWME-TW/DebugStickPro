package dev.twme.debugstickpro.localization;

import dev.twme.debugstickpro.DebugStickPro;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Locale;

public class NewLangFile {
    private File file;
    private YamlConfiguration langFile;
    public NewLangFile(String locale) {
        String langFileName = locale + ".yml";
        file = new File(DebugStickPro.getInstance().getDataFolder(), "lang/" + langFileName);

        if (!file.exists()) {

        }

    }
}
