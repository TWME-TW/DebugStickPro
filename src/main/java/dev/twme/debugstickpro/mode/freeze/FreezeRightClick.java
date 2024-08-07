package dev.twme.debugstickpro.mode.freeze;

import dev.twme.debugstickpro.events.FreezeBlockEvent;
import dev.twme.debugstickpro.events.UnFreezeBlockEvent;
import dev.twme.debugstickpro.utils.AutoCheckCanChangeUtil;
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

            UnFreezeBlockEvent unFreezeBlockEvent = new UnFreezeBlockEvent(playerUUID, block);
            Bukkit.getPluginManager().callEvent(unFreezeBlockEvent);
            if (unFreezeBlockEvent.isCancelled()) {
                return;
            }
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
