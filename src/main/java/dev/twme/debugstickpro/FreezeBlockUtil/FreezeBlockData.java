package dev.twme.debugstickpro.FreezeBlockUtil;

import org.bukkit.block.Block;

import org.bukkit.entity.Entity;

public class FreezeBlockData {
    private Entity itemDisplay;
    private Entity blockDisplay;
    private Block block;
    private String blockString;

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
    public Block getBlock(){
        return block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FreezeBlockData)) return false;

        FreezeBlockData thisData = (FreezeBlockData) o;
        if (!thisData.getItemDisplay().equals(this.getItemDisplay())) {
            return false;
        }

        if (! thisData.getBlock().getLocation().equals(this.getBlock().getLocation())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemDisplay.hashCode();
        result = 31 * result + block.hashCode();
        return result;
    }
}
