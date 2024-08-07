package dev.twme.debugstickpro.events;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/*
 * This event is called when a player unfreezes a block
 */

public class UnFreezeBlockEvent extends Event implements Cancellable {
    private boolean isCancelled = false;
    private static final HandlerList HANDLERS = new HandlerList();
    private final UUID playerUUID;
    private final Block block;

    public UnFreezeBlockEvent(UUID playerUUID, Block block) {
        this.playerUUID = playerUUID;
        this.block = block;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.isCancelled = b;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public Block getBlock() {
        return block;
    }
}
