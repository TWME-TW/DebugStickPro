package dev.twme.debugstickpro.mode.classic;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.blockdatautil.BlockDataSeparater;
import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.events.ClassicModeBlockBlockDataChangingEvent;
import dev.twme.debugstickpro.events.ClassicModeChangedBlockEvent;
import dev.twme.debugstickpro.events.ClassicModeChangingBlockEvent;
import dev.twme.debugstickpro.hook.CoreProtectUtil;
import dev.twme.debugstickpro.playerdata.PlayerData;
import dev.twme.debugstickpro.util.AutoCheckCanChangeUtil;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
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

        ClassicModeChangingBlockEvent event = new ClassicModeChangingBlockEvent(playerUUID, block);
        Bukkit.getPluginManager().callEvent(event);

        if (event.isCancelled()) {
            return;
        }

        if (!AutoCheckCanChangeUtil.canChange(playerUUID, block)) {
            return;
        }

        ArrayList<SubBlockData> subBlockDataList = BlockDataSeparater.separate(block);

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
                    previousData(playerUUID, block, subBlockData);
                } else {
                    nextData(playerUUID, block, subBlockData);
                }
                block.getState().update();
                callClassicModeChangedBlockEvent(playerUUID, block);
                break;
            }
        }

        CoreProtectUtil.logBlockPlace(player.getName(), block.getLocation(), block.getBlockData());
    }

    // TODO

    private static void nextData(UUID playerUUID, Block block, SubBlockData subBlockData) {
        BlockData oldBlockData = DebugStickPro.getInstance().getServer().createBlockData(block.getBlockData().getAsString());
        BlockData newBlockData = subBlockData.nextData().getBlockData();
        ClassicModeBlockBlockDataChangingEvent event = new ClassicModeBlockBlockDataChangingEvent(playerUUID, block, oldBlockData, newBlockData);

        if (event.isCancelled()) {
            return;
        }

        block.setBlockData(event.getNewBlockData(), false);
    }

    private static void previousData(UUID playerUUID, Block block, SubBlockData subBlockData) {
        BlockData oldBlockData = DebugStickPro.getInstance().getServer().createBlockData(block.getBlockData().getAsString());
        BlockData newBlockData = subBlockData.previousData().getBlockData();
        ClassicModeBlockBlockDataChangingEvent event = new ClassicModeBlockBlockDataChangingEvent(playerUUID, block, oldBlockData, newBlockData);

        if (event.isCancelled()) {
            return;
        }

        block.setBlockData(event.getNewBlockData(), false);
    }

    private static void callClassicModeChangedBlockEvent(UUID playerUUID, Block block) {
        ClassicModeChangedBlockEvent event = new ClassicModeChangedBlockEvent(playerUUID, block);
        Bukkit.getPluginManager().callEvent(event);
    }
}
