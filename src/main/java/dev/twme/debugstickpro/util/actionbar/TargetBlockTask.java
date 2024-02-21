package dev.twme.debugstickpro.util.actionbar;

import dev.twme.debugstickpro.util.player.playerdata.PlayerData;
import dev.twme.debugstickpro.util.player.playerdata.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
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


            Block block = player.getTargetBlockExact(5);
            if (CheckBlockCanUseUtil.check(block)) {

                BlockData blockData = block.getBlockData();
                PlayerData playerData = PlayerDataManager.getPlayerData(uuid);
                playerData.setDisplaySubBlockData(block);


                return;
            } else {
                ActionbarUtil.removeActionBar(uuid);
            }
        }
    }
}
