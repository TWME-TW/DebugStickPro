package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Leaves;

public class LeavesDistanceData implements SubBlockData{
    private String NAME = "Leaves Distance";
    private BlockData blockData;
    private int distance;
    public LeavesDistanceData(BlockData blockData){
        this.blockData = blockData;
        this.distance = ((Leaves) blockData).getDistance();
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
        nextDistanceProperty();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Distance: " + distance;
    }

    @Override
    public String getNextAsString() {
        nextDistanceProperty();
        return "Distance: " + distance;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(distance);
    }

    @Override
    public String getNextDataAsString() {
        nextDistanceProperty();
        return String.valueOf(distance);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextDistanceProperty(){
        Leaves leaves = (Leaves) blockData;
        if (distance >= leaves.getMaximumDistance()) {
            distance = leaves.getMinimumDistance();
        } else {
            distance++;
        }
        leaves.setDistance(distance);
    }
}
