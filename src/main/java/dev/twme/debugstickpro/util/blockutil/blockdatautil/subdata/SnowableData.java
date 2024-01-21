package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Snowable;

public class SnowableData implements SubBlockData{
    private String NAME = "Snowable";
    private BlockData blockData;
    private boolean snowy;
    public SnowableData(BlockData blockData){
        this.blockData = blockData;
        this.snowy = ((Snowable) blockData).isSnowy();
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
        return "Snowy: " + snowy;
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "Snowy: " + snowy;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(snowy);
    }

    @Override
    public String getNextDataAsString() {
        return String.valueOf(snowy);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData(){
        Snowable snowable = ((Snowable) blockData);
        snowy = !snowy;
        snowable.setSnowy(snowy);
    }
}
