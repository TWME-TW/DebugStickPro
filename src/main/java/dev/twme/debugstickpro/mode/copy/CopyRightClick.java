package dev.twme.debugstickpro.mode.copy;

import dev.twme.debugstickpro.blockdatautil.BlockDataSeparater;
import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.events.PasteBlockDataEvent;
import dev.twme.debugstickpro.hook.CoreProtectUtil;
import dev.twme.debugstickpro.playerdata.PlayerData;
import dev.twme.debugstickpro.util.AutoCheckCanChangeUtil;
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

        PasteBlockDataEvent event = new PasteBlockDataEvent(playerUUID, block);
        Bukkit.getPluginManager().callEvent(event);

        if (event.isCancelled()) {
            return;
        }

        if (!AutoCheckCanChangeUtil.canChange(playerUUID, block)) {
            return;
        }

        // 正式開始進入

        // 紀錄玩家操作
        CoreProtectUtil.logBlockBreak(player.getName(), block.getLocation(), block.getBlockData());

        ArrayList<SubBlockData> subBlockDataList = BlockDataSeparater.separate(block);

        for (SubBlockData subBlockData : subBlockDataList) {
            for (SubBlockData copiedSubBlockData : playerData.getCopiedSubBlockData()) {
                if (subBlockData.name().equals(copiedSubBlockData.name())) {
                    block.setBlockData(copiedSubBlockData.copyTo(subBlockData.getBlockData()), false);
                    block.getState().update();
                    break;
                }
            }
        }

        CoreProtectUtil.logBlockPlace(player.getName(), block.getLocation(), block.getBlockData());
    }
}
