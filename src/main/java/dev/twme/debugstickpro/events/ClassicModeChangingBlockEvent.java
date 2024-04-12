package dev.twme.debugstickpro.events;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

// when a player changing(not changed) the block of a block in classic mode

/**
 * This event is called when a player changing(not changed) the block of a block in classic mode
 */
public class ClassicModeChangingBlockEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final UUID playerUUID;
    private final Block block;

    private boolean isCancelled = false;

    public ClassicModeChangingBlockEvent(UUID playerUUID, Block block) {
        this.playerUUID = playerUUID;
        this.block = block;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(playerUUID);
    }

    public Block getBlock() {
        return block;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }
}
