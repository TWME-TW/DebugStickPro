package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;

public interface SubBlockData {
    public abstract String NAME();
    public abstract String getData();

    public abstract BlockData setNextData();

    public abstract String getAsString();

}
