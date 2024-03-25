package dev.twme.debugstickpro.config;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashSet;

public class ConfigFile {

    public static int ConfigVersion;

    public static ArrayList<String> LangFiles = new ArrayList<>();


    public static class ActionBarDisplay {
        public static boolean AutoToCenter;
        public static long UpdateInterval;
    }

    public static class DebugStickItem {
        public static Material Material;
        public static String DisplayName;
        public static ArrayList<Component> Lore;

        public static class CustomModelData {
            public static boolean Enabled;
            public static int CustomModelData;
        }
    }

    public static class WhitelistWorlds {
        public static boolean Enabled;
        public static HashSet<String> Worlds;
    }

    public static class BlacklistWorlds {
        public static boolean Enabled;
        public static HashSet<String> Worlds;
    }

    public static class AutoRegionProtection {
        public static boolean Enabled;
    }

    public static class BlockDataFilter {
        public static class Whitelist {
            public static boolean Enabled;
            public static HashSet<String> Whitelist;
        }

        public static class Blacklist {
            public static boolean Enabled;
            public static HashSet<String> Blacklist;
        }
    }

    public static class ModeSetting {
        public static class ClassicMode {
            public static boolean ClearSelectedDataTypeWhenModeChange;
        }

        public static class CopyMode {
            public static boolean ClearStoredDataWhenModeChange;
        }

        public static class FreezeMode {
            public static boolean UnfreezeAllBlockWhenModeChange;
        }
    }
}
