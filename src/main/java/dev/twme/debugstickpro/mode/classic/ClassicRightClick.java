package dev.twme.debugstickpro.mode.classic;

import dev.twme.debugstickpro.blockdatautil.BlockDataSeparater;
import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.playerdata.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class ClassicRightClick {
    public static void changeSelectedSubBlockDataValue(UUID playerUUID, PlayerData playerData) {

        Player player = Bukkit.getPlayer(playerUUID);

        Block block = player.getTargetBlockExact(5);

        if (block == null) {
            return;
        }

        ArrayList<SubBlockData> subBlockDataList = BlockDataSeparater.Separate(block);

        if (subBlockDataList.isEmpty()) {
            return;
        }

        if (playerData.getSelectedSubBlockDataType() == null) {
            playerData.setSelectedSubBlockDayaType(subBlockDataList.get(0).name());
        }

        boolean hasIsUsingType = false;

        // 將與玩家原本相同類型的 SubBlockData 類型設為 True
        for (SubBlockData subBlockData : subBlockDataList) {
            if (subBlockData.name().equals(playerData.getSelectedSubBlockDataType())) {
                subBlockData.setIsUsing(true);
                hasIsUsingType = true;
                break;
            }
        }

        if (!hasIsUsingType) {
            subBlockDataList.get(0).setIsUsing(true);
        }

        for (SubBlockData subBlockData : subBlockDataList) {
            if (subBlockData.isUsing()) {
                block.setBlockData(subBlockData.nextData().getBlockData(),false);
                block.getState().update();
                break;
            }
        }
    }
}
