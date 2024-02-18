package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Hangable;

public class HangableData implements SubBlockData{
    private String NAME = "Hangable";
    private BlockData blockData;
    private boolean hangable;
    private boolean isUsing =  false;
    public HangableData(BlockData blockData){
        this.blockData = blockData;
        this.hangable = ((Hangable) blockData).isHanging();
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
    public String getAsString() {
        return "Hangable: " + hangable;
    }



    @Override
    public String getDataAsString() {
        return String.valueOf(hangable);
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
        Hangable hangable = ((Hangable) blockData);
        hangable.setHanging(!hangable.isHanging());
        this.hangable = hangable.isHanging();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Hangable)blockData).setHanging(hangable);
        return blockData;
    }
}
