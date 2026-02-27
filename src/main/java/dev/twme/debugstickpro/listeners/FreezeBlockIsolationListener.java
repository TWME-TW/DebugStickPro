package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.mode.freeze.FreezeBlockManager;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Rail;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FreezeBlockIsolationListener implements Listener {
    private static final BlockFace[] HORIZONTAL_FACES = {
            BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST
    };

    @EventHandler(ignoreCancelled = true)
    public void onBlockPhysics(BlockPhysicsEvent event) {
        correctRailShapeIfNeeded(event.getBlock());
        if (isFrozen(event.getBlock()) || isFrozen(event.getSourceBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onLeavesDecay(LeavesDecayEvent event) {
        if (isFrozen(event.getBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockFade(BlockFadeEvent event) {
        if (isFrozen(event.getBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockForm(BlockFormEvent event) {
        if (isFrozen(event.getBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockFromTo(BlockFromToEvent event) {
        if (isFrozen(event.getBlock()) || isFrozen(event.getToBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockGrow(BlockGrowEvent event) {
        if (isFrozen(event.getBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockSpread(BlockSpreadEvent event) {
        if (isFrozen(event.getBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        if (isFrozen(event.getBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockExplode(BlockExplodeEvent event) {
        event.blockList().removeIf(FreezeBlockIsolationListener::isFrozen);
        if (isFrozen(event.getBlock())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityExplode(EntityExplodeEvent event) {
        event.blockList().removeIf(FreezeBlockIsolationListener::isFrozen);
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockPistonExtend(BlockPistonExtendEvent event) {
        if (isFrozen(event.getBlock())
                || containsFrozen(event.getBlocks())
                || containsFrozenAtDestination(event.getBlocks(), event.getDirection())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockPistonRetract(BlockPistonRetractEvent event) {
        if (isFrozen(event.getBlock())
                || containsFrozen(event.getBlocks())
                || containsFrozenAtDestination(event.getBlocks(), event.getDirection().getOppositeFace())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        Block placedBlock = event.getBlockPlaced();
        correctRailShapeIfNeeded(placedBlock);
        for (BlockFace face : HORIZONTAL_FACES) {
            correctRailShapeIfNeeded(placedBlock.getRelative(face));
        }
    }

    private static boolean containsFrozen(Iterable<Block> blocks) {
        for (Block block : blocks) {
            if (isFrozen(block)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsFrozenAtDestination(Iterable<Block> blocks, BlockFace direction) {
        for (Block block : blocks) {
            if (isFrozen(block.getRelative(direction))) {
                return true;
            }
        }
        return false;
    }

    private static boolean isFrozen(Block block) {
        return FreezeBlockManager.isFreezeBlock(block.getLocation());
    }

    private static void correctRailShapeIfNeeded(Block block) {
        if (isFrozen(block)) {
            return;
        }

        BlockData blockData = block.getBlockData();
        if (!(blockData instanceof Rail rail)) {
            return;
        }

        if (!hasFrozenRailNeighbor(block)) {
            return;
        }

        Rail.Shape currentShape = rail.getShape();
        Rail.Shape correctedShape = chooseShapeIgnoringFrozenNeighbors(block, rail, currentShape);
        if (correctedShape == currentShape) {
            return;
        }

        rail.setShape(correctedShape);
        block.setBlockData(rail, false);
    }

    private static Rail.Shape chooseShapeIgnoringFrozenNeighbors(Block block, Rail rail, Rail.Shape currentShape) {
        Set<Rail.Shape> supportedShapes = rail.getShapes();
        List<Rail.Shape> candidates = new ArrayList<>();
        Set<Rail.Shape> added = new HashSet<>();

        boolean north = isNonFrozenRail(block.getRelative(BlockFace.NORTH));
        boolean south = isNonFrozenRail(block.getRelative(BlockFace.SOUTH));
        boolean east = isNonFrozenRail(block.getRelative(BlockFace.EAST));
        boolean west = isNonFrozenRail(block.getRelative(BlockFace.WEST));

        if (north && south) addCandidate(candidates, added, Rail.Shape.NORTH_SOUTH);
        if (east && west) addCandidate(candidates, added, Rail.Shape.EAST_WEST);
        if (north && east) addCandidate(candidates, added, Rail.Shape.NORTH_EAST);
        if (north && west) addCandidate(candidates, added, Rail.Shape.NORTH_WEST);
        if (south && east) addCandidate(candidates, added, Rail.Shape.SOUTH_EAST);
        if (south && west) addCandidate(candidates, added, Rail.Shape.SOUTH_WEST);

        if (north || south) addCandidate(candidates, added, Rail.Shape.NORTH_SOUTH);
        if (east || west) addCandidate(candidates, added, Rail.Shape.EAST_WEST);

        // If only frozen rail neighbors are present, choose the perpendicular straight shape.
        if (!north && !south && !east && !west) {
            boolean frozenNorthSouth = isFrozenRail(block.getRelative(BlockFace.NORTH))
                    || isFrozenRail(block.getRelative(BlockFace.SOUTH));
            boolean frozenEastWest = isFrozenRail(block.getRelative(BlockFace.EAST))
                    || isFrozenRail(block.getRelative(BlockFace.WEST));

            if (frozenEastWest) addCandidate(candidates, added, Rail.Shape.NORTH_SOUTH);
            if (frozenNorthSouth) addCandidate(candidates, added, Rail.Shape.EAST_WEST);
        }

        addCandidate(candidates, added, currentShape);

        for (Rail.Shape candidate : candidates) {
            if (!supportedShapes.contains(candidate)) {
                continue;
            }
            if (!shapeTouchesFrozenRailNeighbor(block, candidate)) {
                return candidate;
            }
        }

        for (Rail.Shape candidate : candidates) {
            if (supportedShapes.contains(candidate)) {
                return candidate;
            }
        }

        return currentShape;
    }

    private static void addCandidate(List<Rail.Shape> candidates, Set<Rail.Shape> added, Rail.Shape shape) {
        if (added.add(shape)) {
            candidates.add(shape);
        }
    }

    private static boolean shapeTouchesFrozenRailNeighbor(Block block, Rail.Shape shape) {
        BlockFace[] connectedFaces = getConnectedFaces(shape);
        for (BlockFace face : connectedFaces) {
            if (isFrozenRail(block.getRelative(face))) {
                return true;
            }
        }
        return false;
    }

    private static BlockFace[] getConnectedFaces(Rail.Shape shape) {
        return switch (shape) {
            case NORTH_SOUTH, ASCENDING_NORTH, ASCENDING_SOUTH -> new BlockFace[]{BlockFace.NORTH, BlockFace.SOUTH};
            case EAST_WEST, ASCENDING_EAST, ASCENDING_WEST -> new BlockFace[]{BlockFace.EAST, BlockFace.WEST};
            case NORTH_EAST -> new BlockFace[]{BlockFace.NORTH, BlockFace.EAST};
            case NORTH_WEST -> new BlockFace[]{BlockFace.NORTH, BlockFace.WEST};
            case SOUTH_EAST -> new BlockFace[]{BlockFace.SOUTH, BlockFace.EAST};
            case SOUTH_WEST -> new BlockFace[]{BlockFace.SOUTH, BlockFace.WEST};
        };
    }

    private static boolean hasFrozenRailNeighbor(Block block) {
        for (BlockFace face : HORIZONTAL_FACES) {
            if (isFrozenRail(block.getRelative(face))) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNonFrozenRail(Block block) {
        return !isFrozen(block) && block.getBlockData() instanceof Rail;
    }

    private static boolean isFrozenRail(Block block) {
        return isFrozen(block) && block.getBlockData() instanceof Rail;
    }
}
