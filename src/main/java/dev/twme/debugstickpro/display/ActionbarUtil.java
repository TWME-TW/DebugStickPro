package dev.twme.debugstickpro.display;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

public class ActionbarUtil {

    private static final Set<UUID> lastIsRemove = new java.util.HashSet<>();
    public static void removeActionBar(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        if (lastIsRemove.contains(uuid)) {
            return;
        }
        if (player != null) {
            player.sendActionBar(Component.text(" "));
            lastIsRemove.add(uuid);
        }
    }

    public static void sendActionBar(Player player, String message) {
        var mm = MiniMessage.miniMessage();
        Component parsed = mm.deserialize(message);
        player.sendActionBar(parsed);
        lastIsRemove.remove(player.getUniqueId());
    }
}
