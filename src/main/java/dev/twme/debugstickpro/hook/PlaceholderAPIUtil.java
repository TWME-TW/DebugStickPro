package dev.twme.debugstickpro.hook;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class PlaceholderAPIUtil {
    private static boolean isPlaceholderAPILoaded = false;

    public static boolean initPlaceholderAPI() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            isPlaceholderAPILoaded = true;
            return true;
        } else {
            isPlaceholderAPILoaded = false;
            return false;
        }
    }

    public static String insertPAPI(Player player, String text) {
        if (isPlaceholderAPILoaded && player != null) {
            return PlaceholderAPI.setPlaceholders(player, text);
        } else {
            return text;
        }
    }

    public static List<String> insertPAPI(Player player, List<String> list) {
        if (isPlaceholderAPILoaded && player != null) {
            return PlaceholderAPI.setPlaceholders(player, list);
        } else {
            return list;
        }
    }
}
