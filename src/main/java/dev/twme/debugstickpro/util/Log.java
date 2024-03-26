package dev.twme.debugstickpro.util;

import org.bukkit.Bukkit;

public final class Log {
    private static final String prefix = "[DebugStickPro] ";

    public static void info(String message) {
        Bukkit.getLogger().info(prefix + message);
    }

    public static void warning(String message) {
        Bukkit.getLogger().warning(prefix + message);
    }

    public static void announcement(String message) {
        info(message);
        Bukkit.getServer().getOnlinePlayers().forEach(player -> player.sendMessage(prefix + message));
    }
}
