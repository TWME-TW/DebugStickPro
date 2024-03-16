package dev.twme.debugstickpro.configs;

import java.util.HashSet;

public class ConfigFile {
    public static boolean AutoToCenterEnabled = true;

    public static class CustomModelData{
        public static boolean Enabled = false;
        public static int CustomModelData = 0;
    }

    public static class WhitelistWorlds{
        public static boolean Enabled = false;
        public static HashSet<String> Worlds = new HashSet<>();
    }

    public static class BlacklistWorlds{
        public static boolean Enabled = false;
        public static HashSet<String> Worlds = new HashSet<>();
    }

    public static class AutoRegionProtectionEnabled {
        public static boolean Enabled = true;
    }

}
