package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;

public interface SubBlockData {

    public String name();

    public String getDisplayName();

    public abstract BlockData getData();

    public abstract String getAsString();

    public abstract String getDataAsString();

    public abstract void setIsUsing(boolean isUsing);
    public abstract boolean isUsing();

    public abstract SubBlockData nextData();
    public abstract BlockData copyTo(BlockData blockData);
}
