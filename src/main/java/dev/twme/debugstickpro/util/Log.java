package dev.twme.debugstickpro.util;

import org.bukkit.Bukkit;

public class Log {
    public static void info(String message) {
        Bukkit.getLogger().info(message);
    }
    public static void warning(String message) {
        Bukkit.getLogger().warning(message);
    }
}
