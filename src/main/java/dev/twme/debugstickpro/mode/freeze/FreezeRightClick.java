package dev.twme.debugstickpro.mode.freeze;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.UUID;

public class FreezeRightClick {
    public static void onRightClick(UUID playerUUID) {

        Player player = Bukkit.getPlayer(playerUUID);
        Block block = player.getTargetBlockExact(5);

        if (block == null) {
            return;
        }

        if (FreezeBlockManager.isFreezeBlock(block.getLocation())) {
            FreezeBlockManager.removeBlock(playerUUID, block);
        } else {
            FreezeBlockManager.addBlock(playerUUID, block);
        }
    }
}
