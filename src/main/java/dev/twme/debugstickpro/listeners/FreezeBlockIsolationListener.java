package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.mode.freeze.FreezeBlockManager;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class FreezeBlockIsolationListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onBlockPhysics(BlockPhysicsEvent event) {
        boolean protectedTarget = FreezeBlockManager.maintainProtectedDependent(event.getBlock());
        boolean protectedSource = !protectedTarget && FreezeBlockManager.maintainProtectedDependent(event.getSourceBlock());
        if (protectedTarget || protectedSource) {
            event.setCancelled(true);
            return;
        }

        if (isFrozenOrProtected(event.getBlock()) || isFrozenOrProtected(event.getSourceBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onLeavesDecay(LeavesDecayEvent event) {
        if (isFrozenOrProtected(event.getBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockFade(BlockFadeEvent event) {
        if (isFrozenOrProtected(event.getBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockForm(BlockFormEvent event) {
        if (isFrozenOrProtected(event.getBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockFromTo(BlockFromToEvent event) {
        if (isFrozenOrProtected(event.getBlock()) || isFrozenOrProtected(event.getToBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockGrow(BlockGrowEvent event) {
        if (isFrozenOrProtected(event.getBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockSpread(BlockSpreadEvent event) {
        if (isFrozenOrProtected(event.getBlock()) || isFrozenOrProtected(event.getSource())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        if (isFrozenOrProtected(event.getBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockExplode(BlockExplodeEvent event) {
        event.blockList().removeIf(FreezeBlockIsolationListener::isFrozenOrProtected);
        if (isFrozenOrProtected(event.getBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityExplode(EntityExplodeEvent event) {
        event.blockList().removeIf(FreezeBlockIsolationListener::isFrozenOrProtected);
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockPistonExtend(BlockPistonExtendEvent event) {
        if (isFrozenOrProtected(event.getBlock())
                || containsFrozenOrProtected(event.getBlocks())
                || containsFrozenOrProtectedAtDestination(event.getBlocks(), event.getDirection())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockPistonRetract(BlockPistonRetractEvent event) {
        if (isFrozenOrProtected(event.getBlock())
                || containsFrozenOrProtected(event.getBlocks())
                || containsFrozenOrProtectedAtDestination(event.getBlocks(), event.getDirection().getOppositeFace())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        FreezeBlockManager.removeProtectedDependent(event.getBlockPlaced().getLocation());
    }

    private static boolean containsFrozenOrProtected(Iterable<Block> blocks) {
        for (Block block : blocks) {
            if (isFrozenOrProtected(block)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsFrozenOrProtectedAtDestination(Iterable<Block> blocks, BlockFace direction) {
        for (Block block : blocks) {
            if (isFrozenOrProtected(block.getRelative(direction))) {
                return true;
            }
        }
        return false;
    }

    private static boolean isFrozenOrProtected(Block block) {
        return isFrozen(block) || isProtected(block);
    }

    private static boolean isFrozen(Block block) {
        return FreezeBlockManager.isFreezeBlock(block.getLocation());
    }

    private static boolean isProtected(Block block) {
        return FreezeBlockManager.isProtectedDependent(block.getLocation());
    }
}
