package dev.twme.debugstickpro.mode.freeze;

import io.github.twme.virtualentities.VirtualEntity;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;

import java.util.UUID;

/**
 * One frozen block. The server holds a barrier at the location while clients
 * are shown the frozen state through fake block packets.
 */
public class FreezeBlockData {
    private final UUID owner;
    private final FreezeLocation location;
    private final BlockState originalState;
    private final String blockString;
    private final int clientStateId;
    private final VirtualEntity outline;

    public FreezeBlockData(UUID owner, Block block, int clientStateId, VirtualEntity outline) {
        this.owner = owner;
        this.location = FreezeLocation.of(block);
        this.originalState = block.getState();
        this.blockString = block.getBlockData().getAsString();
        this.clientStateId = clientStateId;
        this.outline = outline;
    }

    public UUID getOwner() {
        return owner;
    }

    public FreezeLocation getLocation() {
        return location;
    }

    public BlockState getOriginalState() {
        return originalState;
    }

    public String getBlockString() {
        return blockString;
    }

    public Material getOriginalMaterial() {
        return originalState.getType();
    }

    /**
     * @return the server-version global block state id shown to clients
     */
    public int getClientStateId() {
        return clientStateId;
    }

    public VirtualEntity getOutline() {
        return outline;
    }
}
