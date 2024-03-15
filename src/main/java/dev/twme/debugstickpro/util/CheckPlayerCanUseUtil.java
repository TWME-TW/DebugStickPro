package dev.twme.debugstickpro.util;

import dev.twme.debugstickpro.playerdata.NewPlayerDataManager;
import dev.twme.debugstickpro.util.DebugStickItemCheck;
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
            if (!DebugStickItemCheck.checkPlayer(player)){
                return returnFalse(player);
            }
        }
        return true;
    }

    private static boolean returnFalse(Player player) {
        NewPlayerDataManager.removePlayerFromDisplayList(player.getUniqueId());
        return false;
    }
}
