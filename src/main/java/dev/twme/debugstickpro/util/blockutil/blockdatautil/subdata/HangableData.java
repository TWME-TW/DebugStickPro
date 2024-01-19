package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Hangable;

public class HangableData implements SubBlockData{
    private String NAME = "Hangable";
    private BlockData blockData;
    private boolean hangable;
    public HangableData(BlockData blockData){
        this.blockData = blockData;
        this.hangable = ((Hangable) blockData).isHanging();
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
        nextHangableProperty();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Hangable: " + hangable;
    }

    @Override
    public String getNextAsString() {
        nextHangableProperty();
        return "Hangable: " + hangable;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(hangable);
    }

    @Override
    public String getNextDataAsString() {
        nextHangableProperty();
        return String.valueOf(hangable);
    }

    private void nextHangableProperty(){
        Hangable hangable = ((Hangable) blockData);
        hangable.setHanging(!hangable.isHanging());
        this.hangable = hangable.isHanging();
    }
}
