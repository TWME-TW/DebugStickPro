package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Farmland;

public class FarmlandData implements SubBlockData{
    private String NAME = "Farmland Moisture";
    private BlockData blockData;
    private int moisture;
    private boolean isUsing = false;
    public FarmlandData(BlockData blockData) {
        this.blockData = blockData;
        this.moisture = ((Farmland) blockData).getMoisture();
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
        return "Moisture: " + moisture;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(moisture);
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

    public SubBlockData nextData() {
        Farmland farmland = (Farmland) blockData;
        if (moisture >= farmland.getMaximumMoisture()) {
            moisture = 0;
        } else {
            moisture++;
        }
        farmland.setMoisture(moisture);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        Farmland farmland = (Farmland) blockData;
        return blockData;
    }
}
