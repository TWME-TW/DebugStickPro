package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Hatchable;

public class HatchableData implements SubBlockData{
    private String NAME = "Hatchable";
    private BlockData blockData;
    private int hatch;
    public HatchableData(BlockData blockData){
        this.blockData = blockData;
        this.hatch = ((Hatchable) blockData).getHatch();
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
        nextHatchProperty();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Hatch: " + hatch;
    }

    @Override
    public String getNextAsString() {
        nextHatchProperty();
        return "Hatch: " + hatch;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(hatch);
    }

    @Override
    public String getNextDataAsString() {
        nextHatchProperty();
        return String.valueOf(hatch);
    }
    private void nextHatchProperty(){
        Hatchable hatchable = ((Hatchable) blockData);
        if (hatchable.getHatch() >= hatchable.getMaximumHatch()) {
            hatchable.setHatch(1);
        } else {
            hatchable.setHatch(hatchable.getHatch() + 1);
        }
        this.hatch = hatchable.getHatch();
    }
}
