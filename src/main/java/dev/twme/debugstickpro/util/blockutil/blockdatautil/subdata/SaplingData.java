package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Sapling;

public class SaplingData implements SubBlockData{
    private String NAME = "Sapling";
    private BlockData blockData;
    private int stage;
    public SaplingData(BlockData blockData) {
        this.blockData = blockData;
        this.stage = ((Sapling) blockData).getStage();
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
        return "Stage: " + stage;
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "Stage: " + stage;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(stage);
    }

    @Override
    public String getNextDataAsString() {
        nextData();
        return String.valueOf(stage);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData(){
        Sapling sapling = ((Sapling) blockData);
        if (stage >= sapling.getMaximumStage()){
            stage = 0;
        } else {
            stage++;
        }
        sapling.setStage(stage);
    }
}
