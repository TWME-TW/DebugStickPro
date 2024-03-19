package dev.twme.debugstickpro.mode.freeze;

import dev.twme.debugstickpro.events.FreezeBlockEvent;
import dev.twme.debugstickpro.util.AutoCheckCanChangeUtil;
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

        if (!AutoCheckCanChangeUtil.canChange(playerUUID, block)) {
            return;
        }

        if (FreezeBlockManager.isFreezeBlock(block.getLocation())) {
            // remove a freeze block
            FreezeBlockManager.removeOneBlock(playerUUID, block);
        } else {
            // start add a freeze block
            FreezeBlockEvent event = new FreezeBlockEvent(playerUUID, block);
            Bukkit.getPluginManager().callEvent(event);
            // if event is cancelled, do nothing
            if (event.isCancelled()) {
                return;
            }
            FreezeBlockManager.freezeBlock(playerUUID, block);
        }
    }
}
