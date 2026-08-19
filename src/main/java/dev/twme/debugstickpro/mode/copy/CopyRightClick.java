package dev.twme.debugstickpro.mode.copy;

import dev.twme.debugstickpro.blockdatautil.BlockDataSeparater;
import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.events.CopyModeChangingBlockEvent;
import dev.twme.debugstickpro.events.CopyModePasteBlockDataEvent;
import dev.twme.debugstickpro.hook.CoreProtectUtil;
import dev.twme.debugstickpro.playerdata.PlayerData;
import dev.twme.debugstickpro.utils.AutoCheckCanChangeUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.profile.PlayerTextures;
import org.bukkit.profile.PlayerProfile;

import java.util.List;
import java.util.UUID;

public class CopyRightClick {
    public static void onRightClick(UUID playerUUID, PlayerData playerData, Block block) {
        Player player = Bukkit.getPlayer(playerUUID);

        if (block == null) {
            return;
        }

        CopyModePasteBlockDataEvent event = new CopyModePasteBlockDataEvent(playerUUID, block);
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

        if (block.getType() == Material.PLAYER_HEAD) {
            if (block.getState() instanceof Skull) {
                Skull skull = (Skull) block.getState();
                if (playerData.getCopiedSkullBlockPlayerProfile() != null) {
                    skull.setOwnerProfile(playerData.getCopiedSkullBlockPlayerProfile());
                } else {
                    if (skull.hasOwner()) {
                        PlayerProfile playerProfile = skull.getOwnerProfile();
                        PlayerTextures textures = playerProfile.getTextures();
                        textures.clear();
                        playerProfile.setTextures(textures);
                        skull.setOwnerProfile(playerProfile);
                    }
                }
                skull.update(true);
            }
        }

        List<SubBlockData> oldSubBlockDataList = BlockDataSeparater.separate(block, playerUUID);
        List<SubBlockData> newSubBlockDataList = playerData.getCopiedSubBlockData();

        CopyModeChangingBlockEvent copyModeChangingBlockEvent = new CopyModeChangingBlockEvent(playerUUID, block, oldSubBlockDataList, newSubBlockDataList);
        Bukkit.getPluginManager().callEvent(copyModeChangingBlockEvent);

        if (event.isCancelled()) {
            return;
        }

        oldSubBlockDataList = copyModeChangingBlockEvent.getOldBlockData();
        newSubBlockDataList = copyModeChangingBlockEvent.getNewBlockData();

        for (SubBlockData subBlockData : oldSubBlockDataList) {
            for (SubBlockData copiedSubBlockData : newSubBlockDataList) {
                if (subBlockData.name().equals(copiedSubBlockData.name())) {
                    block.setBlockData(copiedSubBlockData.copyTo(subBlockData.getBlockData()), false);
                    block.getState().update();
                    break;
                }
            }
        }

        block.getState().update(true);
        CoreProtectUtil.logBlockPlace(player.getName(), block.getLocation(), block.getBlockData());
    }
}
