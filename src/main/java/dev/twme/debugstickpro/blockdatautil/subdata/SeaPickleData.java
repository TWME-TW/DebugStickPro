package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.SeaPickle;

public class SeaPickleData extends SubBlockData {
    private int pickles;

    public SeaPickleData(BlockData blockData) {
        this.blockData = blockData;
        this.pickles = ((SeaPickle) blockData).getPickles();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.SeaPickleDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(pickles);
    }

    public SubBlockData nextData() {
        SeaPickle seaPickle = ((SeaPickle) blockData);
        if (pickles >= seaPickle.getMaximumPickles()) {
            pickles = seaPickle.getMinimumPickles();
        } else {
            pickles++;
        }
        seaPickle.setPickles(pickles);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        SeaPickle seaPickle = ((SeaPickle) blockData);
        if (pickles <= seaPickle.getMinimumPickles()) {
            pickles = seaPickle.getMaximumPickles();
        } else {
            pickles--;
        }
        seaPickle.setPickles(pickles);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((SeaPickle) blockData).setPickles(pickles);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new SeaPickleData(blockData);
    }
}
