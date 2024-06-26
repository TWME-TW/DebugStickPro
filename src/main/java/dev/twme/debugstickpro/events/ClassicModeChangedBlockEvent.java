package dev.twme.debugstickpro.events;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

// when a player changes (changed) the block of a block in classic mode

/**
 * This event is called when a player changes (changed) the block of a block in classic mode
 */
public class ClassicModeChangedBlockEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private final Block block;
    private final UUID playerUUID;

    /**
     * Create a new ClassicModeChangedBlockEvent
     *
     * @param playerUUID the UUID of the player
     * @param block the block
     */
    public ClassicModeChangedBlockEvent(UUID playerUUID, Block block) {
        this.playerUUID = playerUUID;
        this.block = block;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(playerUUID);
    }

    public Block getBlock() {
        return block;
    }


    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
