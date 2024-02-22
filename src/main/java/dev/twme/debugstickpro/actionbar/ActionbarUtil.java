package dev.twme.debugstickpro.actionbar;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ActionbarUtil {
    public static void removeActionBar(UUID uuid) {
        if (Bukkit.getPlayer(uuid) != null) {
            Bukkit.getPlayer(uuid).sendActionBar(Component.text(" "));
        }
    }
    public static void sendActionBar(Player player, String message) {
        var mm = MiniMessage.miniMessage();
        Component parsed = mm.deserialize(message);
        player.sendActionBar(parsed);
    }
}
