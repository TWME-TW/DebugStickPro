package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Brushable;

public class BrushableData implements SubBlockData{
    private String NAME = "Dusted";
    private BlockData blockData;
    private int dusted;
    public BrushableData(BlockData blockData){
        this.blockData = blockData;
        this.dusted = ((Brushable) blockData).getDusted();
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
        nextDustedProperty();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Dusted: " + dusted;
    }

    @Override
    public String getNextAsString() {
        nextDustedProperty();
        return "Dusted: " + dusted;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(dusted);
    }

    @Override
    public String getNextDataAsString() {
        nextDustedProperty();
        return String.valueOf(dusted);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextDustedProperty(){
        Brushable brushable = ((Brushable) blockData);
        if (brushable.getDusted() >= brushable.getMaximumDusted()){
            brushable.setDusted(0);
        } else {
            brushable.setDusted(brushable.getDusted() + 1);
        }
        this.dusted = brushable.getDusted();
    }
}
