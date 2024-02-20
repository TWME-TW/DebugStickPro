package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Hatchable;

public class HatchableData implements SubBlockData {
    private String NAME = "Hatchable";
    private BlockData blockData;
    private int hatch;
    private boolean isUsing = false;

    public HatchableData(BlockData blockData) {
        this.blockData = blockData;
        this.hatch = ((Hatchable) blockData).getHatch();
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String dataName() {
        return LangFile.HatchableDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(hatch);
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

    @Override
    public SubBlockData nextData() {
        Hatchable hatchable = ((Hatchable) blockData);
        if (hatchable.getHatch() >= hatchable.getMaximumHatch()) {
            hatchable.setHatch(1);
        } else {
            hatchable.setHatch(hatchable.getHatch() + 1);
        }
        this.hatch = hatchable.getHatch();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Hatchable) blockData).setHatch(hatch);
        return blockData;
    }
}
