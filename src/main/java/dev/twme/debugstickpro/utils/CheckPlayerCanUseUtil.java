package dev.twme.debugstickpro.utils;

import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import org.bukkit.entity.Player;

public final class CheckPlayerCanUseUtil {
    public static boolean check(Player player) {
        return check(player, true);
    }

    public static boolean check(Player player, boolean checkItem) {
        if (player == null) {
            return false;
        }
        if (!player.isOnline()) {
            removePlayerFromDisplayList(player);
            return false;
        }
        if (!player.hasPermission("debugstickpro.use")) {
            removePlayerFromDisplayList(player);
            return false;
        }
        if (checkItem) {
            if (!DebugStickItem.checkPlayer(player)) {
                removePlayerFromDisplayList(player);
                return false;
            }
        }
        return true;
    }

    private static void removePlayerFromDisplayList(Player player) {
        PlayerDataManager.removePlayerFromDisplayList(player.getUniqueId());
    }
}
