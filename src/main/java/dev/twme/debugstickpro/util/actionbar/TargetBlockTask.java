package dev.twme.debugstickpro.util.actionbar;

import dev.twme.debugstickpro.util.DebugStickItemCheck;
import dev.twme.debugstickpro.util.blockutil.blockdatautil.BlockDatas;
import dev.twme.debugstickpro.util.player.playerdata.PlayerDataManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class TargetBlockTask implements Runnable {
    @Override
    public void run() {
        for (UUID uuid : PlayerDataManager.getPlayerEnableDisplay()) {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null){
                return;
            }
            // TODO: 需檢查其效能占用率
            if (!player.hasPermission("debugstickpro.use")){
                PlayerDataManager.removePlayerEnableDisplay(uuid);
                return;
            }

            if (!DebugStickItemCheck.checkPlayer(player)){
                PlayerDataManager.removePlayerEnableDisplay(uuid);
                return;
            }
            if (player.getTargetBlockExact(5) != null) {
                Block block = player.getTargetBlockExact(5);
                BlockData blockData = null;

                if (block != null) {
                    blockData = block.getBlockData();
                }
                if (blockData != null) {

                    player.sendActionBar(Component.text("Test: " + new BlockDatas(block).getAsString()));
                }
            } else {
                player.sendActionBar(Component.text(" "));
            }
        }
    }

}
