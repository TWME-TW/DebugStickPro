package dev.twme.debugstickpro.util.actionbar;

import dev.twme.debugstickpro.util.player.playerdata.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ActionDisplayTask implements Runnable {
    @Override
    public void run() {
        for (UUID uuid : PlayerDataManager.getPlayerEnableDisplay()) {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null) {
                return;
            }
            if (!player.hasPermission("debugstickpro.use")) {
                ActionbarUtil.sendActionBar(player, " ");
                PlayerDataManager.removePlayerEnableDisplay(uuid);
                return;
            }
            if (!player.isOnline()) {
                PlayerDataManager.removePlayerEnableDisplay(uuid);
                return;
            }
            String s = PlayerDataManager.getPlayerData(uuid).getBlockData().getAsString();
            ActionbarUtil.sendActionBar(player, s);
        }
    }
}
