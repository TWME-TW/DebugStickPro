package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.SeaPickle;

public class SeaPickleData implements SubBlockData{
    private String NAME = "SeaPickle";
    private BlockData blockData;
    private int pickles;
    public SeaPickleData(BlockData blockData){
        this.blockData = blockData;
        this.pickles = ((SeaPickle) blockData).getPickles();
    }
    @Override
    public String name() {
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
        return "Pickles: " + pickles;
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "Pickles: " + pickles;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(pickles);
    }

    @Override
    public String getNextDataAsString() {
        nextData();
        return String.valueOf(pickles);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData(){
        SeaPickle seaPickle = ((SeaPickle) blockData);
        if (pickles >= seaPickle.getMaximumPickles()){
            pickles = seaPickle.getMinimumPickles();
        } else {
            pickles++;
        }
        seaPickle.setPickles(pickles);
    }
}
