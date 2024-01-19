package dev.twme.debugstickpro.util.actionbar;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ActionbarUtil {
    public static void removeActionBar(UUID uuid) {
        ActionBarTask.playerList.remove(uuid);
        Bukkit.getPlayer(uuid).sendActionBar(Component.text(" "));
    }
    public static void sendActionBar(Player player, String message) {
        player.sendActionBar(Component.text(message));
    }
}
