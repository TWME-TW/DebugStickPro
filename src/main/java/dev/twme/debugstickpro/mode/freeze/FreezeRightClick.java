package dev.twme.debugstickpro.mode.freeze;

import dev.twme.debugstickpro.events.FreezeModeFreezeBlockEvent;
import dev.twme.debugstickpro.events.FreezeModeUnFreezeBlockEvent;
import dev.twme.debugstickpro.utils.AutoCheckCanChangeUtil;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.FaceAttachable;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import java.util.UUID;

public class FreezeRightClick {
    private static final BlockFace[] SURROUNDING_FACES = {
            BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN
    };

    public static void onRightClick(UUID playerUUID, Action action, Block clickedBlock, BlockFace clickedFace) {

        Player player = Bukkit.getPlayer(playerUUID);
        if (player == null) {
            return;
        }

        Block block = resolveTargetBlock(player, action, clickedBlock, clickedFace);

        if (block == null) {
            return;
        }

        if (!AutoCheckCanChangeUtil.canChange(playerUUID, block)) {
            return;
        }

        if (FreezeBlockManager.isFreezeBlock(block.getLocation())) {
            // remove a freeze block

            FreezeModeUnFreezeBlockEvent freezeModeUnFreezeBlockEvent = new FreezeModeUnFreezeBlockEvent(playerUUID, block);
            Bukkit.getPluginManager().callEvent(freezeModeUnFreezeBlockEvent);
            if (freezeModeUnFreezeBlockEvent.isCancelled()) {
                return;
            }
            FreezeBlockManager.removeOneBlock(playerUUID, block);
        } else {
            // start add a freeze block
            FreezeModeFreezeBlockEvent event = new FreezeModeFreezeBlockEvent(playerUUID, block);
            Bukkit.getPluginManager().callEvent(event);
            // if event is cancelled, do nothing
            if (event.isCancelled()) {
                return;
            }
            FreezeBlockManager.freezeBlock(playerUUID, block);
        }
    }

    private static Block resolveTargetBlock(Player player, Action action, Block clickedBlock, BlockFace clickedFace) {
        if (action == Action.RIGHT_CLICK_BLOCK && clickedBlock != null) {
            if (FreezeBlockManager.isFreezeBlock(clickedBlock.getLocation())) {
                return clickedBlock;
            }

            Block supportBlock = resolveAttachedSupport(clickedBlock, clickedFace);
            if (supportBlock != null) {
                return supportBlock;
            }

            Block adjacentFrozenBlock = resolveAdjacentFrozenBlock(clickedBlock, clickedFace);
            if (adjacentFrozenBlock != null) {
                return adjacentFrozenBlock;
            }

            return clickedBlock;
        }

        if (action == Action.RIGHT_CLICK_AIR || clickedBlock == null) {
            return player.getTargetBlockExact(5);
        }

        return null;
    }

    private static Block resolveAttachedSupport(Block clickedBlock, BlockFace clickedFace) {
        if (!(clickedBlock.getBlockData() instanceof FaceAttachable)) {
            return null;
        }

        if (clickedFace != null && clickedFace != BlockFace.SELF) {
            Block supportFromFace = clickedBlock.getRelative(clickedFace.getOppositeFace());
            if (FreezeBlockManager.isFreezeBlock(supportFromFace.getLocation())) {
                return supportFromFace;
            }
        }

        for (BlockFace face : SURROUNDING_FACES) {
            Block support = clickedBlock.getRelative(face);
            if (FreezeBlockManager.isFreezeBlock(support.getLocation())) {
                return support;
            }
        }

        return null;
    }

    private static Block resolveAdjacentFrozenBlock(Block clickedBlock, BlockFace clickedFace) {
        if (clickedFace != null && clickedFace != BlockFace.SELF) {
            Block preferred = clickedBlock.getRelative(clickedFace.getOppositeFace());
            if (FreezeBlockManager.isFreezeBlock(preferred.getLocation())) {
                return preferred;
            }
        }

        Block found = null;
        for (BlockFace face : SURROUNDING_FACES) {
            Block neighbor = clickedBlock.getRelative(face);
            if (!FreezeBlockManager.isFreezeBlock(neighbor.getLocation())) {
                continue;
            }

            if (found != null) {
                // Ambiguous case: multiple frozen neighbors around the clicked block.
                return null;
            }
            found = neighbor;
        }

        return found;
    }
}
