package dev.twme.debugstickpro.mode.copy;

import dev.twme.debugstickpro.blockdatautil.BlockDataSeparater;
import dev.twme.debugstickpro.blockdatautil.subdata.SubBlockData;
import dev.twme.debugstickpro.playerdata.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class CopyRightClick {
    public static void onRightClick(UUID playerUUID, PlayerData playerData) {
        Player player = Bukkit.getPlayer(playerUUID);
        Block block = player.getTargetBlockExact(5);

        if (block == null) {
            return;
        }

        ArrayList<SubBlockData> subBlockDataList = BlockDataSeparater.Separate(block);

        for (SubBlockData subBlockData : subBlockDataList) {
            for (SubBlockData copiedSubBlockData : playerData.getCopiedSubBlockData()) {
                if (subBlockData.name().equals(copiedSubBlockData.name())) {
                    block.setBlockData(copiedSubBlockData.copyTo(subBlockData.getBlockData()), false);
                    block.getState().update();
                    break;
                }
            }
        }
    }
}
