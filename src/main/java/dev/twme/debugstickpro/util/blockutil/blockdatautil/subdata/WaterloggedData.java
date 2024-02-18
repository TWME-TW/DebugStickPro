package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Waterlogged;

public class WaterloggedData implements SubBlockData{
    private BlockData blockData;
    private boolean waterlogged;
    private boolean isUsing = false;
    public WaterloggedData(BlockData blockData){
        this.blockData = blockData;
        this.waterlogged = ((Waterlogged)blockData).isWaterlogged();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public BlockData getData() {
        return blockData;
    }


    @Override
    public String getAsString() {
        return "Waterlogged: " + waterlogged;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(waterlogged);
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

    public SubBlockData nextData(){
        Waterlogged waterlogged = ((Waterlogged) blockData);
        this.waterlogged = !waterlogged.isWaterlogged();
        waterlogged.setWaterlogged(this.waterlogged);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Waterlogged)blockData).setWaterlogged(waterlogged);
        return blockData;
    }
}
