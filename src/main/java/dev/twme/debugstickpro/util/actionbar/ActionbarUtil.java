package dev.twme.debugstickpro.util.actionbar;

import dev.twme.debugstickpro.util.player.playerdata.PlayerDataManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ActionbarUtil {
    public static void removeActionBar(UUID uuid) {
        PlayerDataManager.removePlayerEnableDisplay(uuid);
        if (Bukkit.getPlayer(uuid) != null) {
            Bukkit.getPlayer(uuid).sendActionBar(Component.text(" "));
        }
    }
    public static void sendActionBar(Player player, String message) {
        player.sendActionBar(Component.text(message));
    }
}
