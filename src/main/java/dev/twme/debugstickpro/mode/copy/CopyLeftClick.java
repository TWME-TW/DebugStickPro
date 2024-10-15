package dev.twme.debugstickpro.mode.copy;

import dev.twme.debugstickpro.blockdatautil.BlockDataSeparater;
import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.events.CopyModeCopyBlockDataEvent;
import dev.twme.debugstickpro.playerdata.PlayerData;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import dev.twme.debugstickpro.utils.AutoCheckCanChangeUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class CopyLeftClick {
    public static void onLeftClick(UUID playerUUID, PlayerData playerData) {
        Player player = Bukkit.getPlayer(playerUUID);
        Block block = player.getTargetBlockExact(5);

        if (block == null) {
            return;
        }

        CopyModeCopyBlockDataEvent event = new CopyModeCopyBlockDataEvent(playerUUID, block);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return;
        }

        if (!AutoCheckCanChangeUtil.canChange(playerUUID, block)) {
            return;
        }

        if (Material.PLAYER_HEAD == block.getType()) {

        }

        ArrayList<SubBlockData> subBlockDataList = BlockDataSeparater.separate(block);
        playerData.setCopiedSubBlockData(subBlockDataList);

        if (block.getType() == Material.PLAYER_HEAD) {
            if (block.getState() instanceof Skull) {
                if (((Skull) block.getState()).hasOwner()) {
                    playerData.setCopiedSkullBlockPlayerProfile(((Skull) block.getState()).getPlayerProfile());
                }
            }
        } else {
            playerData.removePlayerProfile();
        }
        PlayerDataManager.setPlayerData(playerUUID, playerData);
    }
}
