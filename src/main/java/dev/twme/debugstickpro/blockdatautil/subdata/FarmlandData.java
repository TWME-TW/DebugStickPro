package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Farmland;

public class FarmlandData implements SubBlockData {

    private final BlockData blockData;
    private int moisture;
    private boolean isUsing = false;

    public FarmlandData(BlockData blockData) {
        this.blockData = blockData;
        this.moisture = ((Farmland) blockData).getMoisture();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.FarmlandDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
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
    public SubBlockData previousData() {
        Farmland farmland = (Farmland) blockData;
        if (moisture <= 0) {
            moisture = farmland.getMaximumMoisture();
        } else {
            moisture--;
        }
        farmland.setMoisture(moisture);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        Farmland farmland = (Farmland) blockData;
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new FarmlandData(blockData);
    }
}
