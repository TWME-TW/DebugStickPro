package dev.twme.debugstickpro.utils;

import org.bukkit.Bukkit;

import java.util.HashMap;

public final class Log {
    private static final HashMap<String,Long> warnCooldown = new HashMap<>();

    private static final String prefix = "[DebugStickPro] ";

    public static void info(String message) {
        Bukkit.getLogger().info(prefix + message);
    }

    public static void warning(String message) {
        if (warnCooldown.containsKey(message) && warnCooldown.get(message) + 10 * 1000 > System.currentTimeMillis()) {
            return;
        }
        Bukkit.getLogger().warning(prefix + message);
        warnCooldown.put(message, System.currentTimeMillis());
    }

    public static void announcement(String message) {
        info(message);
        Bukkit.getServer().getOnlinePlayers().forEach(player -> player.sendMessage(prefix + message));
    }
}
