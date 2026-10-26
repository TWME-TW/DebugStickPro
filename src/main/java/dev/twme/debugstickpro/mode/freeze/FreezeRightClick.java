package dev.twme.debugstickpro.mode.freeze;

import dev.twme.debugstickpro.events.FreezeModeFreezeBlockEvent;
import dev.twme.debugstickpro.events.FreezeModeUnFreezeBlockEvent;
import dev.twme.debugstickpro.utils.AutoCheckCanChangeUtil;
import org.bukkit.Bukkit;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class FreezeRightClick {
    private static final int TARGET_DISTANCE = 5;

    /**
     * A client follows a passed block interaction with an item use; both arrive together.
     */
    private static final long FOLLOW_UP_WINDOW_NANOS = 100_000_000L;

    private static final Map<UUID, Long> lastBlockInteraction = new ConcurrentHashMap<>();

    public static void onRightClick(UUID playerUUID, Action action, Block clickedBlock) {

        Player player = Bukkit.getPlayer(playerUUID);
        if (player == null) {
            return;
        }

        long now = System.nanoTime();
        if (action == Action.RIGHT_CLICK_BLOCK) {
            lastBlockInteraction.put(playerUUID, now);
        } else {
            Long last = lastBlockInteraction.get(playerUUID);
            if (last != null && now - last < FOLLOW_UP_WINDOW_NANOS) {
                // Otherwise the follow-up would toggle the block that was just frozen back off.
                return;
            }
        }

        Block block = resolveTargetBlock(player, action, clickedBlock);
        if (block == null) {
            return;
        }

        FreezeBlockData frozenData = FreezeBlockManager.getFrozenBlock(block);
        if (frozenData != null) {
            // The server block is the barrier anchor; check permissions against the frozen block.
            if (!AutoCheckCanChangeUtil.canChange(playerUUID, block, frozenData.getOriginalMaterial())) {
                return;
            }

            FreezeModeUnFreezeBlockEvent freezeModeUnFreezeBlockEvent = new FreezeModeUnFreezeBlockEvent(playerUUID, block);
            Bukkit.getPluginManager().callEvent(freezeModeUnFreezeBlockEvent);
            if (freezeModeUnFreezeBlockEvent.isCancelled()) {
                return;
            }
            FreezeBlockManager.unfreezeBlock(block);
            return;
        }

        if (block.getType().isAir() || !AutoCheckCanChangeUtil.canChange(playerUUID, block)) {
            return;
        }

        FreezeModeFreezeBlockEvent event = new FreezeModeFreezeBlockEvent(playerUUID, block);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return;
        }
        FreezeBlockManager.freezeBlock(playerUUID, block);
    }

    /**
     * Frozen blocks occupy their whole cell on the server even when the client shows a smaller
     * shape, so a frozen block hit by the server ray trace before the clicked block wins.
     */
    private static Block resolveTargetBlock(Player player, Action action, Block clickedBlock) {
        if (action == Action.RIGHT_CLICK_BLOCK && clickedBlock != null) {
            Block frozenOnSightLine = findFrozenOnSightLine(player, clickedBlock);
            return frozenOnSightLine != null ? frozenOnSightLine : clickedBlock;
        }

        // The client hit nothing, e.g. a frozen fluid or the empty part of a frozen torch.
        // The server ray trace stops at barrier anchors, so frozen blocks are found as well.
        return player.getTargetBlockExact(TARGET_DISTANCE);
    }

    private static Block findFrozenOnSightLine(Player player, Block clickedBlock) {
        if (clickedBlock.getWorld() != player.getWorld()) {
            return null;
        }

        // Trace towards the clicked block rather than along the server-side rotation, which can
        // lag behind the click. An exact trace also avoids BlockIterator, which yields cells
        // beside the ray and used to unfreeze a neighbor of the clicked block.
        Location eye = player.getEyeLocation();
        Vector towardsClicked = clickedBlock.getLocation().add(0.5, 0.5, 0.5).toVector().subtract(eye.toVector());
        double distance = towardsClicked.length();
        if (distance < 1.0E-6) {
            return null;
        }
        RayTraceResult hit = player.getWorld().rayTraceBlocks(eye, towardsClicked, distance, FluidCollisionMode.NEVER);
        Block hitBlock = hit == null ? null : hit.getHitBlock();
        if (hitBlock != null && !hitBlock.equals(clickedBlock) && FreezeBlockManager.isFreezeBlock(hitBlock)) {
            return hitBlock;
        }
        return null;
    }
}
