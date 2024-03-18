package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Hatchable;

public class HatchableData implements SubBlockData {
    private final BlockData blockData;
    private int hatch;
    private boolean isUsing = false;

    public HatchableData(BlockData blockData) {
        this.blockData = blockData;
        this.hatch = ((Hatchable) blockData).getHatch();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.HatchableDataName;
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
    public SubBlockData previousData() {
        Hatchable hatchable = ((Hatchable) blockData);
        if (hatchable.getHatch() <= 0) {
            hatchable.setHatch(hatchable.getMaximumHatch());
        } else {
            hatchable.setHatch(hatchable.getHatch() - 1);
        }
        this.hatch = hatchable.getHatch();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Hatchable) blockData).setHatch(hatch);
        return blockData;
    }

    @Override
    public SubBlockData getDataFac(BlockData blockData) {
        return new HatchableData(blockData);
    }
}
