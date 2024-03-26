package dev.twme.debugstickpro.mode.freeze;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public class FreezeBlockData {
    private final Entity itemDisplay;
    private final Entity blockDisplay;
    private final Block block;
    private final String blockString;

    public FreezeBlockData(Entity itemDisplay, Entity blockDisplay, Block block) {
        this.itemDisplay = itemDisplay;
        this.blockDisplay = blockDisplay;
        this.block = block;
        this.blockString = block.getBlockData().getAsString();
    }

    public Entity getItemDisplay() {
        return itemDisplay;
    }

    public Entity getBlockDisplay() {
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
