package dev.twme.debugstickpro.util;

import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import org.bukkit.entity.Player;

public class CheckPlayerCanUseUtil {
    public static boolean check(Player player) {
        return check(player,true);
    }

    public static boolean check(Player player,boolean checkItem) {
        if (player == null){
            return false;
        }
        if (!player.isOnline()) {
            return returnFalse(player);
        }
        if (!player.hasPermission("debugstickpro.use")) {
            return returnFalse(player);
        }
        if (checkItem) {
            if (!DebugStickItem.checkPlayer(player)){
                return returnFalse(player);
            }
        }
        return true;
    }

    private static boolean returnFalse(Player player) {
        PlayerDataManager.removePlayerFromDisplayList(player.getUniqueId());
        return false;
    }
}
