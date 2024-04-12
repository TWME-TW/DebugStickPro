package dev.twme.debugstickpro.blockdatautil;

import org.bukkit.block.data.BlockData;

/**
 * This interface is used to represent the SubBlockData
 */
public interface SubBlockData {
    /**
     * Get the name of the SubBlockData
     *
     * @return the name of the SubBlockData
     */

    String name();

    /**
     * Get the name of the SubBlockData
     *
     * @return the name of the SubBlockData
     */

    String dataName();

    /**
     * Get the BlockData of the SubBlockData
     *
     * @return the BlockData of the SubBlockData
     */

    BlockData getBlockData();

    /**
     * Get the String of the BlockData of the SubBlockData
     *
     * @return the String of the BlockData of the SubBlockData
     */
    String getDataAsString();

    /**
     * Set the SubBlockData is using or not
     *
     * @return the BlockData of the SubBlockData
     */

    SubBlockData setIsUsing(boolean isUsing);

    /**
     * Check if the SubBlockData is using
     *
     * @return true if the SubBlockData is using
     */

    boolean isUsing();

    /**
     * Get next BlockData of the SubBlockData
     *
     * @return the BlockData of the SubBlockData
     */
    SubBlockData nextData();

    /**
     * Get previous BlockData of the SubBlockData
     *
     * @return the BlockData of the SubBlockData
     */
    SubBlockData previousData();

    /**
     * Copy the BlockData of the SubBlockData to the BlockData
     *
     * @return the BlockData of the SubBlockData
     */
    BlockData copyTo(BlockData blockData);

    /**
     * Get the BlockData of the SubBlockData
     *
     * @return the BlockData of the SubBlockData
     */
    SubBlockData fromBlockData(BlockData blockData);
}
