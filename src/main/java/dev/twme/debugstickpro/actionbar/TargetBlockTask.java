package dev.twme.debugstickpro.actionbar;

import dev.twme.debugstickpro.playerdata.PlayerData;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TargetBlockTask implements Runnable {
    @Override
    public void run() {
        for (UUID uuid : PlayerDataManager.getPlayerEnableDisplay()) {
            Player player = Bukkit.getPlayer(uuid);

            if (!CheckPlayerCanUseUtil.check(player)) {
                return;
            }

            PlayerData playerData = PlayerDataManager.getPlayerData(uuid);
            Block block = player.getTargetBlockExact(5);
            if (CheckBlockCanUseUtil.check(block)) {

                playerData.setDisplaySubBlockData(block);

                return;
            } else {
                playerData.removeDisplaySubBlockData();
                ActionbarUtil.removeActionBar(uuid);
            }
        }
    }
}
