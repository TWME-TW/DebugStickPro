package dev.twme.debugstickpro.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * This event is called when a player freezes a block
 */
public class FreezeModeFreezeBlockEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final UUID playerUUID;
    private final Block block;
    private boolean isCancelled = false;

    public FreezeModeFreezeBlockEvent(UUID playerUUID, Block block) {
        this.playerUUID = playerUUID;
        this.block = block;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public static Location getLocation(Block block) {
        return block.getLocation();
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    /**
     * Set the cancelled state of this event
     *
     * @param b true if you want to cancel this event
     */
    @Override
    public void setCancelled(boolean b) {
        this.isCancelled = b;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(playerUUID);
    }

    public Block getBlock() {
        return block;
    }
}
