package dev.twme.debugstickpro.blockdatautil;

import org.bukkit.block.data.BlockData;

public interface SubBlockData {

    String name();

    String dataName();

    BlockData getBlockData();

    String getDataAsString();

    SubBlockData setIsUsing(boolean isUsing);

    boolean isUsing();

    SubBlockData nextData();

    SubBlockData previousData();

    BlockData copyTo(BlockData blockData);

    SubBlockData fromBlockData(BlockData blockData);
}
