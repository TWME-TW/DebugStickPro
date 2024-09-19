package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Waterlogged;

public class WaterloggedData extends SubBlockData {
    private boolean waterlogged;

    public WaterloggedData(BlockData blockData) {
        this.blockData = blockData;
        this.waterlogged = ((Waterlogged) blockData).isWaterlogged();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.WaterloggedDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(waterlogged);
    }

    public SubBlockData nextData() {
        Waterlogged waterlogged = ((Waterlogged) blockData);
        this.waterlogged = !waterlogged.isWaterlogged();
        waterlogged.setWaterlogged(this.waterlogged);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Waterlogged) blockData).setWaterlogged(waterlogged);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new WaterloggedData(blockData);
    }
}
