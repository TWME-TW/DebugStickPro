package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.mode.freeze.FreezeBlockManager;
import dev.twme.debugstickpro.utils.Log;
import dev.twme.debugstickpro.utils.SendFakeBarrier;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class BlockPhysicsEventListener implements Listener {

    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event) {
        event.setCancelled(isCancelled(event));
    }
    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent event) {
        event.setCancelled(isCancelled(event));
    }
    @EventHandler
    public void onBlockFade(BlockFadeEvent event) {
        event.setCancelled(isCancelled(event));
    }
    @EventHandler
    public void onBlockForm(BlockFormEvent event) {
        event.setCancelled(isCancelled(event));
    }
    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        event.setCancelled(isCancelled(event));
    }
    @EventHandler
    public void onBlockGrow(BlockGrowEvent event) {
        event.setCancelled(isCancelled(event));
    }
    @EventHandler
    public void onBlockSpread(BlockSpreadEvent event) {
        event.setCancelled(isCancelled(event));
    }
    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        event.setCancelled(isCancelled(event.getBlock().getLocation()));
    }
    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {
        event.setCancelled(isCancelled(event));
    }




    private boolean isCancelled(BlockEvent event) {
        Location loc = event.getBlock().getLocation();
        return isCancelled(loc);
    }

    private boolean isCancelled(Location location) {
        if (FreezeBlockManager.isFreezeBlock(location)) {
            // Cancel the block event if the block is frozen
            SendFakeBarrier.sendFakeBarrierToAllPlayers(location);
            Log.announcement("Block physics event cancelled for frozen block at " + location.toString());
            return true;
        }
        return false;
    }
}
