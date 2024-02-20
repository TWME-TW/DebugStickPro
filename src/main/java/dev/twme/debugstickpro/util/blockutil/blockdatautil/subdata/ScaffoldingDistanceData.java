package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Scaffolding;

public class ScaffoldingDistanceData implements SubBlockData{
    private String NAME = "ScaffoldingDistance";
    private BlockData blockData;
    private int distance;
    private boolean isUsing = false;
    public ScaffoldingDistanceData(BlockData blockData){
        this.blockData = blockData;
        this.distance = ((Scaffolding) blockData).getDistance();
    }
    @Override
    public String name() {
        return NAME;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Distance: " + distance;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(distance);
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

    @Override
    public SubBlockData nextData(){
        Scaffolding scaffolding = ((Scaffolding) blockData);
        if (distance >= scaffolding.getMaximumDistance()){
            distance = 0;
        } else {
            distance++;
        }
        scaffolding.setDistance(distance);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        return null;
    }
}
