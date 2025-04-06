package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Hatchable;

public class HatchableData extends SubBlockData {
    private int hatch;

    public HatchableData(BlockData blockData) {
        this.blockData = blockData;
        this.hatch = ((Hatchable) blockData).getHatch();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.HatchableDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(hatch);
    }

    @Override
    public SubBlockData nextData() {
        Hatchable hatchable = ((Hatchable) blockData);
        if (hatchable.getHatch() >= hatchable.getMaximumHatch()) {
            hatchable.setHatch(0);
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
    public SubBlockData fromBlockData(BlockData blockData) {
        return new HatchableData(blockData);
    }
}
