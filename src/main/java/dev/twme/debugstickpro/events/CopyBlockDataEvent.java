package dev.twme.debugstickpro.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class CopyBlockDataEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled = false;
    private final UUID playerUUID;
    private final Block block;


    public CopyBlockDataEvent(UUID playerUUID, Block block) {
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
    public @Nullable Player getPlayer() {
        return Bukkit.getPlayer(playerUUID);
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }
    public Block getBlock() {
        return block;
    }
    public Location getLocation() {
        return block.getLocation();
    }
}
