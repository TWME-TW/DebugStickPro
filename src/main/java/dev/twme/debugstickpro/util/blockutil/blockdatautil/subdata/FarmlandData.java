package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Farmland;

public class FarmlandData implements SubBlockData{
    private String NAME = "Farmland Moisture";
    private BlockData blockData;
    private int moisture;
    public FarmlandData(BlockData blockData) {
        this.blockData = blockData;
        this.moisture = ((Farmland) blockData).getMoisture();
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
        nextMoisture();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Moisture: " + moisture;
    }

    @Override
    public String getNextAsString() {
        nextMoisture();
        return "Moisture: " + moisture;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(moisture);
    }

    @Override
    public String getNextDataAsString() {
        return String.valueOf(moisture);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextMoisture() {
        Farmland farmland = (Farmland) blockData;
        if (moisture >= farmland.getMaximumMoisture()) {
            moisture = 0;
        } else {
            moisture++;
        }
        farmland.setMoisture(moisture);
    }
}
