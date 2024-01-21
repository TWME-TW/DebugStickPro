package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Waterlogged;

public class WaterloggedData implements SubBlockData{
    private BlockData blockData;
    private boolean waterlogged;
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
    public BlockData getNextData() {
        nextData();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Waterlogged: " + waterlogged;
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "Waterlogged: " + waterlogged;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(waterlogged);
    }

    @Override
    public String getNextDataAsString() {
        nextData();
        return String.valueOf(waterlogged);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextData(){
        Waterlogged waterlogged = ((Waterlogged) blockData);
        this.waterlogged = !waterlogged.isWaterlogged();
        waterlogged.setWaterlogged(this.waterlogged);
    }
}
