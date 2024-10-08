package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Sapling;

public class SaplingData extends SubBlockData {
    private int stage;

    public SaplingData(BlockData blockData) {
        this.blockData = blockData;
        this.stage = ((Sapling) blockData).getStage();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.SaplingDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(stage);
    }

    @Override
    public SaplingData nextData() {
        Sapling sapling = ((Sapling) blockData);
        if (stage >= sapling.getMaximumStage()) {
            stage = 0;
        } else {
            stage++;
        }
        sapling.setStage(stage);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Sapling sapling = ((Sapling) blockData);
        if (stage <= 0) {
            stage = sapling.getMaximumStage();
        } else {
            stage--;
        }
        sapling.setStage(stage);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Sapling) blockData).setStage(stage);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new SaplingData(blockData);
    }
}
