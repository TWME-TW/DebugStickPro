package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Sapling;

public class SaplingData implements SubBlockData {
    private final BlockData blockData;
    private int stage;
    private boolean isUsing = false;

    public SaplingData(BlockData blockData) {
        this.blockData = blockData;
        this.stage = ((Sapling) blockData).getStage();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.SaplingDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(stage);
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
