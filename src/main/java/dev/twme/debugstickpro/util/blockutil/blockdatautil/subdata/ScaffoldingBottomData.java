package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Scaffolding;

public class ScaffoldingBottomData implements SubBlockData{
    private String NAME = "ScaffoldingBottom";
    private BlockData blockData;
    private boolean bottom;
    public ScaffoldingBottomData(BlockData blockData){
        this.blockData = blockData;
        this.bottom = ((Scaffolding) blockData).isBottom();
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
        return "Bottom: " + bottom;
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "Bottom: " + bottom;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(bottom);
    }

    @Override
    public String getNextDataAsString() {
        nextData();
        return String.valueOf(bottom);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextData(){
        Scaffolding scaffolding = ((Scaffolding) blockData);
        bottom = !bottom;
        scaffolding.setBottom(bottom);
    }
}
