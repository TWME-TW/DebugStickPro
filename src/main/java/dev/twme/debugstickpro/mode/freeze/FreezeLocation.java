package dev.twme.debugstickpro.mode.freeze;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.UUID;

/**
 * Immutable block coordinate that does not keep a world reference alive.
 */
public record FreezeLocation(UUID worldUUID, int x, int y, int z) {

    public static FreezeLocation of(Location location) {
        return new FreezeLocation(location.getWorld().getUID(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public static FreezeLocation of(Block block) {
        return new FreezeLocation(block.getWorld().getUID(), block.getX(), block.getY(), block.getZ());
    }

    public FreezeChunk chunk() {
        return new FreezeChunk(worldUUID, x >> 4, z >> 4);
    }

    /**
     * @return the block at this location, or null when its world is no longer loaded
     */
    public Block getBlock() {
        World world = Bukkit.getWorld(worldUUID);
        return world == null ? null : world.getBlockAt(x, y, z);
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld(worldUUID), x, y, z);
    }

    public record FreezeChunk(UUID worldUUID, int x, int z) {
    }
}
