package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Scaffolding;

public class ScaffoldingDistanceData implements SubBlockData{
    private String NAME = "ScaffoldingDistance";
    private BlockData blockData;
    private int distance;
    public ScaffoldingDistanceData(BlockData blockData){
        this.blockData = blockData;
        this.distance = ((Scaffolding) blockData).getDistance();
    }
    @Override
    public String NAME() {
        return NAME;
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
        return "Distance: " + distance;
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "Distance: " + distance;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(distance);
    }

    @Override
    public String getNextDataAsString() {
        nextData();
        return String.valueOf(distance);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData(){
        Scaffolding scaffolding = ((Scaffolding) blockData);
        if (distance >= scaffolding.getMaximumDistance()){
            distance = 0;
        } else {
            distance++;
        }
        scaffolding.setDistance(distance);
    }
}
