package dev.twme.debugstickpro.blockdatautil;

import org.bukkit.block.data.BlockData;

public interface SubBlockData {

    public String name();
    public String dataName();
    public abstract BlockData getBlockData();
    public abstract String getDataAsString();
    public abstract SubBlockData setIsUsing(boolean isUsing);
    public abstract boolean isUsing();
    public abstract SubBlockData nextData();
    public abstract SubBlockData previousData();
    public abstract BlockData copyTo(BlockData blockData);
}
