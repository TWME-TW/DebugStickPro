package dev.twme.debugstickpro.mode.classic;

import dev.twme.debugstickpro.blockdatautil.BlockDataSeparater;
import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.events.ClassicModeChangeBlockEvent;
import dev.twme.debugstickpro.hook.CoreProtectUtil;
import dev.twme.debugstickpro.playerdata.PlayerData;
import dev.twme.debugstickpro.util.AutoCheckCanChangeUtil;
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

        ClassicModeChangeBlockEvent event = new ClassicModeChangeBlockEvent(playerUUID, block);
        Bukkit.getPluginManager().callEvent(event);

        if (event.isCancelled()) {
            return;
        }

        if (!AutoCheckCanChangeUtil.canChange(playerUUID, block)) {
            return;
        }

        ArrayList<SubBlockData> subBlockDataList = BlockDataSeparater.Separate(block);

        if (subBlockDataList.isEmpty()) {
            return;
        }

        // 正式開始進入

        // 紀錄玩家操作
        CoreProtectUtil.logBlockBreak(player.getName(), block.getLocation(), block.getBlockData());

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
                if (player.isSneaking()) {
                    block.setBlockData(subBlockData.previousData().getBlockData(), false);
                } else {
                    block.setBlockData(subBlockData.nextData().getBlockData(), false);
                }
                block.getState().update();
                break;
            }
        }

        CoreProtectUtil.logBlockPlace(player.getName(), block.getLocation(), block.getBlockData());
    }
}
