package dev.twme.debugstickpro.newblockdata;

import dev.twme.debugstickpro.blockdatautil.subdata.SubBlockData;
import org.bukkit.block.data.BlockData;

public interface NewSubBlockData {
    public String name();
    public String dataName();
    public abstract BlockData getBlockData();
    public abstract String getDataAsString();
    public abstract SubBlockData nextData();
    public abstract BlockData copyTo(BlockData blockData);
}
