package dev.twme.debugstickpro.events;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 * This event is called when a player changes the copy mode of a block
 */
public class CopyModeChangingBlockEvent extends Event implements Cancellable {
    private boolean isCancelled = false;
    private static final HandlerList HANDLERS = new HandlerList();
    private final UUID playerUUID;
    private final Block block;
    private List<SubBlockData> oldBlockData = new ArrayList<>();
    private List<SubBlockData> newBlockData = new ArrayList<>();


    /*
     * Create a new CopyModeChangingBlockEvent
     * @param playerUUID the UUID of the player
     * @param block the block
     * @param oldBlockData the old blockdata
     * @param newBlockData the new blockdata
     */
    public CopyModeChangingBlockEvent(UUID playerUUID, Block block, List<SubBlockData> oldBlockData, List<SubBlockData> newBlockData) {
        this.playerUUID = playerUUID;
        this.block = block;
        this.oldBlockData.addAll(oldBlockData);
        this.newBlockData.addAll(newBlockData);
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
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

    public List<SubBlockData> getOldBlockData() {
        return oldBlockData;
    }

    public List<SubBlockData> getNewBlockData() {
        return newBlockData;
    }

    public void setOldBlockData(List<SubBlockData> oldBlockData) {
        this.oldBlockData = oldBlockData;
    }

    public void setNewBlockData(List<SubBlockData> newBlockData) {
        this.newBlockData = newBlockData;
    }
}
