package dev.twme.debugstickpro.events;

import dev.twme.debugstickpro.playerdata.DebugStickMode;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

// when a player changes the debug stick mode
public class PlayerChangeDebugStickModeEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled = false;
    private final UUID playerUUID;
    private final DebugStickMode previousMode;
    private final DebugStickMode newMode;

    public PlayerChangeDebugStickModeEvent(UUID playerUUID, DebugStickMode previousMode, DebugStickMode newMode) {
        this.playerUUID = playerUUID;
        this.previousMode = previousMode;
        this.newMode = newMode;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(playerUUID);
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public DebugStickMode getPreviousMode() {
        return previousMode;
    }

    public DebugStickMode getNewMode() {
        return newMode;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.isCancelled = b;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
