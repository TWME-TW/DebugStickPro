package dev.twme.debugstickpro.util.actionbar;

import dev.twme.debugstickpro.util.player.playerdata.PlayerData;
import dev.twme.debugstickpro.util.player.playerdata.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ActionDisplayTask implements Runnable {
    @Override
    public void run() {
        for (UUID uuid : PlayerDataManager.getPlayerEnableDisplay()) {
            Player player = Bukkit.getPlayer(uuid);
            if (!CheckPlayerCanUseUtil.check(player)) {
                return;
            }

            PlayerData playerData = PlayerDataManager.getPlayerData(uuid);


            String actionbar = playerData.getDisplaySubBlockData();
            if (actionbar == null) {
                ActionbarUtil.removeActionBar(uuid);
                return;
            }
            ActionbarUtil.sendActionBar(player, actionbar);

        }
    }
}
