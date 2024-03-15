package dev.twme.debugstickpro.mode.copy;

import dev.twme.debugstickpro.blockdatautil.BlockDataSeparater;
import dev.twme.debugstickpro.blockdatautil.subdata.SubBlockData;
import dev.twme.debugstickpro.playerdata.NewPlayerData;
import dev.twme.debugstickpro.playerdata.NewPlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class CopyLeftClick {
    public static void onLeftClick(UUID playerUUID, NewPlayerData playerData) {
        Player player = Bukkit.getPlayer(playerUUID);
        Block block = player.getTargetBlockExact(5);

        if (block == null) {
            return;
        }

        ArrayList<SubBlockData> subBlockDataList = BlockDataSeparater.Separate(block);
        playerData.setCopiedSubBlockData(subBlockDataList);

        NewPlayerDataManager.setPlayerData(playerUUID, playerData);
    }
}
