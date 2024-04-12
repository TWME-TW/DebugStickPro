package dev.twme.debugstickpro.events;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

// when a player changes the block data of a block in classic mode

/**
 * This event is called when a player changes the block data of a block in classic mode
 */
public class ClassicModeBlockBlockDataChangingEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();

    private final UUID playerUUID;
    private final Block block;
    private BlockData oldBlockData;
    private BlockData newBlockData;
    private boolean isCancelled = false;

    /**
     * Create a new ClassicModeBlockBlockDataChangingEvent
     *
     * @param playerUUID the UUID of the player
     * @param block the block
     * @param oldBlockData the old block data
     * @param newBlockData the new block data
     */
    public ClassicModeBlockBlockDataChangingEvent(UUID playerUUID, Block block, BlockData oldBlockData, BlockData newBlockData) {
        this.playerUUID = playerUUID;
        this.block = block;
        this.oldBlockData = oldBlockData;
        this.newBlockData = newBlockData;
    }

    /**
     * Set the old block data
     *
     * @param blockData the old block data
     */
    public void setOldBlockData(BlockData blockData) {
        this.oldBlockData = blockData;
    }

    /**
     * Set the new block data
     *
     * @param blockData the new block data
     */
    public void setNewBlockData(BlockData blockData) {
        this.newBlockData = blockData;
    }

    /**
     * Get the old block data
     *
     * @return the old block data
     */
    public BlockData getOldBlockData() {
        return oldBlockData;
    }

    /**
     * Get the new block data
     *
     * @return the new block data
     */
    public BlockData getNewBlockData() {
        return newBlockData;
    }

    /**
     * Get the UUID of the player
     *
     * @return the UUID of the player
     */
    public UUID getPlayerUUID() {
        return playerUUID;
    }

    /**
     * Get the player
     *
     * @return the player
     */
    public Player getPlayer() {
        return Bukkit.getPlayer(playerUUID);
    }

    /**
     * Get the block
     *
     * @return the block
     */
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

    /**
     * Check if the event is cancelled
     *
     * @return true if the event is cancelled
     */

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    /**
     * Set the event to be cancelled
     *
     * @param b true if the event is to be cancelled
     */
    @Override
    public void setCancelled(boolean b) {
        this.isCancelled = b;
    }
}
