package dev.twme.debugstickpro.mode.freeze;

import dev.twme.debugstickpro.events.FreezeModeFreezeBlockEvent;
import dev.twme.debugstickpro.events.FreezeModeUnFreezeBlockEvent;
import dev.twme.debugstickpro.utils.AutoCheckCanChangeUtil;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.FaceAttachable;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import java.util.UUID;

public class FreezeRightClick {

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
            FreezeModeUnFreezeBlockEvent freezeModeUnFreezeBlockEvent = new FreezeModeUnFreezeBlockEvent(playerUUID, block);
            Bukkit.getPluginManager().callEvent(freezeModeUnFreezeBlockEvent);
            if (freezeModeUnFreezeBlockEvent.isCancelled()) {
                return;
            }
            FreezeBlockManager.removeOneBlock(playerUUID, block);
            return;
        }

        FreezeModeFreezeBlockEvent event = new FreezeModeFreezeBlockEvent(playerUUID, block);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return;
        }
        FreezeBlockManager.freezeBlock(playerUUID, block);
    }

    private static Block resolveTargetBlock(Player player, Action action, Block clickedBlock, BlockFace clickedFace) {
        if (action == Action.RIGHT_CLICK_BLOCK && clickedBlock != null) {
            if (FreezeBlockManager.isFreezeBlock(clickedBlock.getLocation())) {
                return clickedBlock;
            }

            Block supportBlock = resolveFrozenSupportBlock(clickedBlock, clickedFace);
            if (supportBlock != null) {
                return supportBlock;
            }

            return clickedBlock;
        }

        if (action == Action.RIGHT_CLICK_AIR || clickedBlock == null) {
            return player.getTargetBlockExact(5);
        }

        return null;
    }

    private static Block resolveFrozenSupportBlock(Block clickedBlock, BlockFace clickedFace) {
        if (!(clickedBlock.getBlockData() instanceof FaceAttachable faceAttachable)) {
            return null;
        }

        BlockFace supportFace = resolveSupportFace(clickedBlock, faceAttachable, clickedFace);
        if (supportFace == null || supportFace == BlockFace.SELF) {
            return null;
        }

        Block supportBlock = clickedBlock.getRelative(supportFace);
        if (FreezeBlockManager.isFreezeBlock(supportBlock.getLocation())) {
            return supportBlock;
        }

        return null;
    }

    private static BlockFace resolveSupportFace(Block clickedBlock, FaceAttachable faceAttachable, BlockFace clickedFace) {
        return switch (faceAttachable.getAttachedFace()) {
            case FLOOR -> BlockFace.DOWN;
            case CEILING -> BlockFace.UP;
            case WALL -> {
                if (clickedBlock.getBlockData() instanceof Directional directional) {
                    yield directional.getFacing().getOppositeFace();
                }

                if (clickedFace != null && clickedFace != BlockFace.SELF) {
                    yield clickedFace.getOppositeFace();
                }

                yield null;
            }
        };
    }
}
