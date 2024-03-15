package dev.twme.debugstickpro.mode.classic;

import dev.twme.debugstickpro.blockdatautil.BlockDataSeparater;
import dev.twme.debugstickpro.blockdatautil.subdata.SubBlockData;
import dev.twme.debugstickpro.playerdata.NewPlayerData;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class ClassicLeftClick {

    // 更改選擇的 SubBlockData 類型
    public static void changeSelectedSubBlockType(UUID playerUUID, NewPlayerData playerData) {

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
            return;
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