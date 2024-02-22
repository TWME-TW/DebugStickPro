package dev.twme.debugstickpro.events;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class FreezeBlockEvent extends Event implements Cancellable {
    private UUID playerUUID;
    private Block block;
    private static final HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled = false;
    public FreezeBlockEvent(UUID playerUUID, Block block) {
        this.playerUUID = playerUUID;
        this.block = block;
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
    public Player getPlayer() {
        return Bukkit.getPlayer(playerUUID);
    }
    public Block getBlock() {
        return block;
    }
}
