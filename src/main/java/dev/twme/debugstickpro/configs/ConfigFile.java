package dev.twme.debugstickpro.configs;

import java.util.HashSet;

public class ConfigFile {

    public static int ConfigVersion;

    public static class ActionBarDisplay{
        public static boolean AutoToCenter;
        public static long UpdateInterval;
    }

    public static class CustomModelData{
        public static boolean Enabled;
        public static int CustomModelData;
    }

    public static class WhitelistWorlds{
        public static boolean Enabled;
        public static HashSet<String> Worlds;
    }

    public static class BlacklistWorlds{
        public static boolean Enabled;
        public static HashSet<String> Worlds;
    }

    public static class AutoRegionProtection {
        public static boolean Enabled;
    }

}
