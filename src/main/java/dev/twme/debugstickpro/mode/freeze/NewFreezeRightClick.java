package dev.twme.debugstickpro.mode.freeze;

import dev.twme.debugstickpro.util.AutoCheckCanChangeUtil;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NewFreezeRightClick {
    public static void onRightClick(UUID playerUUID) {
        Player player = Bukkit.getPlayer(playerUUID);
        Block block = null;
        if (player != null) {
            block = player.getTargetBlockExact(5);
        }

        if (block == null) {
            return;
        }

        if (!AutoCheckCanChangeUtil.canChange(playerUUID, block)) {
            return;
        }

        if (NewFreezeBlockManager.isFreezeBlock(block)) {
            NewFreezeBlockManager.removeOneFreezeBlock(playerUUID, block);
        } else {
            NewFreezeBlockManager.addFreezeBlock(playerUUID, block);
        }
    }
}
