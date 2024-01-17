package dev.twme.debugstickpro.util;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class ActionbarUtil {
    public static void sendActionBar(Player player, String message) {
        player.sendActionBar(Component.text(message));
    }
}
