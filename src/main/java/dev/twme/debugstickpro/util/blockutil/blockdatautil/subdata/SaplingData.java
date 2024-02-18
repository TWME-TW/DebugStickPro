package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Sapling;

public class SaplingData implements SubBlockData{
    private String NAME = "Sapling";
    private BlockData blockData;
    private int stage;
    private boolean isUsing = false;
    public SaplingData(BlockData blockData) {
        this.blockData = blockData;
        this.stage = ((Sapling) blockData).getStage();
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
        return "Stage: " + stage;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(stage);
    }


    @Override
    public void setIsUsing(boolean isUsing) {
        this.isUsing = isUsing;
    }

    @Override
    public boolean isUsing() {
        return isUsing;
    }

    @Override
    public SaplingData nextData(){
        Sapling sapling = ((Sapling) blockData);
        if (stage >= sapling.getMaximumStage()){
            stage = 0;
        } else {
            stage++;
        }
        sapling.setStage(stage);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Sapling)blockData).setStage(stage);
        return blockData;
    }
}
