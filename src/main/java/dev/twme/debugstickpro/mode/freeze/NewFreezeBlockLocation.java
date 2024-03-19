package dev.twme.debugstickpro.mode.freeze;

import org.bukkit.Location;
import org.bukkit.generator.WorldInfo;

import java.util.UUID;

public class NewFreezeBlockLocation {
    private final int x;
    private final int y;
    private final int z;
    private final Location location;
    private final UUID worldUUID;
    private UUID playerUUID = null;


    public NewFreezeBlockLocation(Location location, UUID playerUUID) {
        this.location = location;
        this.x = (int) location.getX();
        this.y = (int) location.getY();
        this.z = (int) location.getZ();
        this.worldUUID = location.getWorld().getUID();
        this.playerUUID = playerUUID;
    }

    public NewFreezeBlockLocation(Location location) {
        this.location = location;
        this.x = (int) location.getX();
        this.y = (int) location.getY();
        this.z = (int) location.getZ();
        this.worldUUID = location.getWorld().getUID();
    }

    public void setPlayer(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public Location getLocation() {
        return location;
    }

    public UUID getWorldUUID() {
        return worldUUID;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public boolean hasPlayer() {
        return (playerUUID != null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewFreezeBlockLocation thisData)) return false;

        return thisData.x == this.x && thisData.y == this.y && thisData.z == this.z && thisData.worldUUID.equals(this.worldUUID);
    }

    @Override
    public int hashCode() {
        int result = this.worldUUID.hashCode();
        result = 31 * result + x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }
}
