package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.SeaPickle;

public class SeaPickleData implements SubBlockData{
    private String NAME = "SeaPickle";
    private BlockData blockData;
    private int pickles;
    private boolean isUsing = false;
    public SeaPickleData(BlockData blockData){
        this.blockData = blockData;
        this.pickles = ((SeaPickle) blockData).getPickles();
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
        return "Pickles: " + pickles;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(pickles);
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
        SeaPickle seaPickle = ((SeaPickle) blockData);
        if (pickles >= seaPickle.getMaximumPickles()){
            pickles = seaPickle.getMinimumPickles();
        } else {
            pickles++;
        }
        seaPickle.setPickles(pickles);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((SeaPickle)blockData).setPickles(pickles);
        return blockData;
    }
}
