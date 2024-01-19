package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;

public interface SubBlockData {
    public abstract String NAME();
    public abstract BlockData getData();

    public abstract BlockData getNextData();

    public abstract String getAsString();
    public abstract String getNextAsString();

    public abstract String getDataAsString();
    public abstract String getNextDataAsString();
    public abstract void setIsUsing(boolean isUsing);
}
