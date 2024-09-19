package dev.twme.debugstickpro.blockdatautil;

import org.bukkit.block.data.BlockData;

/**
 * This interface is used to represent the SubBlockData
 */
public abstract class SubBlockData {

    protected BlockData blockData;
    protected boolean isUsing = false;
    /**
     * Get the name of the SubBlockData
     *
     * @return the name of the SubBlockData
     */
    public String name() {
        return this.getClass().getSimpleName();
    }

    /**
     * Get the name of the SubBlockData
     *
     * @return the name of the SubBlockData
     */
    public abstract String dataName();

    /**
     * Get the BlockData of the SubBlockData
     *
     * @return the BlockData of the SubBlockData
     */
    public BlockData getBlockData() {
        return blockData;
    }

    /**
     * Get the String of the BlockData of the SubBlockData
     *
     * @return the String of the BlockData of the SubBlockData
     */
    public abstract String getDataAsString();

    /**
     * Set the SubBlockData is using or not
     *
     * @return the BlockData of the SubBlockData
     */
    public SubBlockData setIsUsing(boolean isUsing) {
        this.isUsing = isUsing;
        return this;
    }

    /**
     * Check if the SubBlockData is using
     *
     * @return true if the SubBlockData is using
     */
    public boolean isUsing() {
        return isUsing;
    }

    /**
     * Get next BlockData of the SubBlockData
     *
     * @return the BlockData of the SubBlockData
     */
    public abstract SubBlockData nextData();

    /**
     * Get previous BlockData of the SubBlockData
     *
     * @return the BlockData of the SubBlockData
     */
    public abstract SubBlockData previousData();

    /**
     * Copy the BlockData of the SubBlockData to the BlockData
     *
     * @return the BlockData of the SubBlockData
     */
    public abstract BlockData copyTo(BlockData blockData);

    /**
     * Get the BlockData of the SubBlockData
     *
     * @return the BlockData of the SubBlockData
     */
    public abstract SubBlockData fromBlockData(BlockData blockData);
}
