package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Wall;

public class WallHeightData implements SubBlockData{
    private BlockData blockData;
    private Wall.Height height;
    public WallHeightData(BlockData blockData){
        this.blockData = blockData;

    }
    @Override
    public String name() {
        return null;
    }

    @Override
    public BlockData getData() {
        return null;
    }

    @Override
    public BlockData getNextData() {
        return null;
    }

    @Override
    public String getAsString() {
        return null;
    }

    @Override
    public String getNextAsString() {
        return null;
    }

    @Override
    public String getDataAsString() {
        return null;
    }

    @Override
    public String getNextDataAsString() {
        return null;
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
}
