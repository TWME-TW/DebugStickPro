package dev.twme.debugstickpro.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;


/*
 * This event is called when a player unfreezes all blocks
 */
public class FreezeModeUnFreezeAllBlockEvent extends Event implements Cancellable {
    private boolean isCancelled = false;
    private static final HandlerList HANDLERS = new HandlerList();
    private final UUID playerUUID;
    private final long blockCount;

    public FreezeModeUnFreezeAllBlockEvent(UUID playerUUID, long blockCount) {
        this.playerUUID = playerUUID;
        this.blockCount = blockCount;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.isCancelled = b;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public long getBlockCount() {
        return blockCount;
    }
}
