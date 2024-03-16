package dev.twme.debugstickpro.mode.classic;

import dev.twme.debugstickpro.blockdatautil.BlockDataSeparater;
import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.playerdata.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class ClassicLeftClick {

    // 更改選擇的 SubBlockData 類型
    public static void changeSelectedSubBlockType(UUID playerUUID, PlayerData playerData) {

        Player player = Bukkit.getPlayer(playerUUID);

        Block block = player.getTargetBlockExact(5);

        if (block == null) {
            return;
        }

        ArrayList<SubBlockData> subBlockDataList = BlockDataSeparater.Separate(block);

        if (subBlockDataList.isEmpty()) {
            return;
        }

        boolean hasType = false;

        for (SubBlockData subBlockData : subBlockDataList) {
            if (subBlockData.name().equals(playerData.getSelectedSubBlockDataType())) {
                hasType = true;
                break;
            }
        }

        if (playerData.getSelectedSubBlockDataType() == null || !hasType) {
            playerData.setSelectedSubBlockDayaType(subBlockDataList.get(0).name());
            // TODO: 檢查查是否需要 return
            // return;
        }

        for (int i = 0; i < subBlockDataList.size(); i++) {
            if (subBlockDataList.get(i).name().equals(playerData.getSelectedSubBlockDataType())) {
                if (i + 1 < subBlockDataList.size()) {
                    playerData.setSelectedSubBlockDayaType(subBlockDataList.get(i + 1).name());
                } else {
                    playerData.setSelectedSubBlockDayaType(subBlockDataList.get(0).name());
                }
                return;
            }
        }
    }
}
