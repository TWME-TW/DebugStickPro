package dev.twme.debugstickpro.events;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ClassicModeChangeBlockEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private UUID playerUUID;
    private Block block;
    private SubBlockData subBlockData;
    private boolean isCancelled = false;

    public ClassicModeChangeBlockEvent(UUID playerUUID, Block block, SubBlockData subBlockData) {
        this.playerUUID = playerUUID;
        this.block = block;
        this.subBlockData = subBlockData;
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

    public SubBlockData getSubBlockData() {
        return subBlockData;
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
