package dev.twme.debugstickpro.mode.freeze;

import dev.twme.debugstickpro.events.FreezeModeFreezeBlockEvent;
import dev.twme.debugstickpro.events.FreezeModeUnFreezeBlockEvent;
import dev.twme.debugstickpro.utils.AutoCheckCanChangeUtil;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.util.BlockIterator;

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
     * shape, so the first frozen cell on the sight line wins over the block the client hit.
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

        int distance = (int) Math.ceil(player.getEyeLocation().distance(clickedBlock.getLocation().add(0.5, 0.5, 0.5))) + 1;
        BlockIterator sightLine = new BlockIterator(player, Math.max(1, distance));
        while (sightLine.hasNext()) {
            Block block = sightLine.next();
            if (block.equals(clickedBlock)) {
                return null;
            }
            if (FreezeBlockManager.isFreezeBlock(block)) {
                return block;
            }
        }
        return null;
    }
}
