package dev.twme.debugstickpro.mode.freeze;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import java.util.UUID;

public class FreezeBlockData {
    private final UUID itemDisplay;
    private final UUID blockDisplay;
    private final Block block;
    private final String blockString;

    public FreezeBlockData(UUID itemDisplay, UUID blockDisplay, Block block) {
        this.itemDisplay = itemDisplay;
        this.blockDisplay = blockDisplay;
        this.block = block;
        this.blockString = block.getBlockData().getAsString();
    }

    public UUID getItemDisplay() {
        return itemDisplay;
    }

    public UUID getBlockDisplay() {
        return blockDisplay;
    }

    public String getBlockString() {
        return blockString;
    }

    public Block getBlock() {
        return block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FreezeBlockData thisData)) return false;

        if (!thisData.getItemDisplay().equals(this.getItemDisplay())) {
            return false;
        }

        return thisData.getBlock().getLocation().equals(this.getBlock().getLocation());
    }

    @Override
    public int hashCode() {
        int result = itemDisplay.hashCode();
        result = 31 * result + block.hashCode();
        return result;
    }
}
