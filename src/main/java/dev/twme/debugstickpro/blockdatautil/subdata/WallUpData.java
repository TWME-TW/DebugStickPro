package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Wall;

public class WallUpData extends SubBlockData {
    private boolean up;

    public WallUpData(BlockData blockData) {
        this.blockData = blockData;
        this.up = ((Wall) blockData).isUp();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.WallUpDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(up);
    }

    public SubBlockData nextData() {
        Wall wall = ((Wall) blockData);
        up = !up;
        wall.setUp(up);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Wall) blockData).setUp(up);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new WallUpData(blockData);
    }
}
