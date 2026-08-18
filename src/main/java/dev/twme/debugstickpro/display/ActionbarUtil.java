package dev.twme.debugstickpro.display;

import dev.twme.debugstickpro.utils.TextUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

public class ActionbarUtil {

    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
    private static final Set<UUID> lastIsRemove = java.util.concurrent.ConcurrentHashMap.newKeySet();

    public static void removeActionBar(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        if (lastIsRemove.contains(uuid)) {
            return;
        }
        if (player != null) {
            TextUtil.sendActionBar(player, Component.text(" "));
            lastIsRemove.add(uuid);
        }
    }

    public static void sendActionBar(Player player, String message) {
        TextUtil.sendActionBar(player, MINI_MESSAGE.deserialize(message));
        lastIsRemove.remove(player.getUniqueId());
    }
}
