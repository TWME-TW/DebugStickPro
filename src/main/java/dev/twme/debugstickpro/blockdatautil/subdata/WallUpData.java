package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Wall;

public class WallUpData implements SubBlockData {
    private BlockData blockData;
    private boolean up;
    private boolean isUsing = false;

    public WallUpData(BlockData blockData) {
        this.blockData = blockData;
        this.up = ((Wall) blockData).isUp();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.WallUpDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(up);
    }


    @Override
    public SubBlockData setIsUsing(boolean isUsing) {
        this.isUsing = isUsing;
        return this;
    }

    @Override
    public boolean isUsing() {
        return isUsing;
    }

    public SubBlockData nextData() {
        Wall wall = ((Wall) blockData);
        up = !up;
        wall.setUp(up);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Wall) blockData).setUp(up);
        return blockData;
    }
}
