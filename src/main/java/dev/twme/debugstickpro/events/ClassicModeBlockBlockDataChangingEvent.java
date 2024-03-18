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

public class ClassicModeBlockBlockDataChangingEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();

    private final UUID playerUUID;
    private Block block;
    private BlockData oldBlockData;
    private BlockData newBlockData;
    private boolean isCancelled = false;

    public ClassicModeBlockBlockDataChangingEvent(UUID playerUUID, Block block, BlockData oldBlockData, BlockData newBlockData) {
        this.playerUUID = playerUUID;
        this.block = block;
        this.oldBlockData = oldBlockData;
        this.newBlockData = newBlockData;
    }

    public void setOldBlockData(BlockData blockData) {
        this.oldBlockData = blockData;
    }

    public void setNewBlockData(BlockData blockData) {
        this.newBlockData = blockData;
    }

    public BlockData getOldBlockData() {
        return oldBlockData;
    }

    public BlockData getNewBlockData() {
        return newBlockData;
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

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.isCancelled = b;
    }
}
