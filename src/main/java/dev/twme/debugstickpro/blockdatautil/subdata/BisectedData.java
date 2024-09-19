package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.BlockData;

public class BisectedData extends SubBlockData {
    private Bisected.Half half;

    public BisectedData(BlockData blockData) {
        this.blockData = blockData;
        this.half = ((Bisected) blockData).getHalf();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.BisectedDataName;
    }


    @Override
    public String getDataAsString() {
        return half.name();
    }


    @Override
    public SubBlockData nextData() {
        Bisected bisected = ((Bisected) blockData);
        if (bisected.getHalf() == Bisected.Half.TOP) {
            bisected.setHalf(Bisected.Half.BOTTOM);
        } else {
            bisected.setHalf(Bisected.Half.TOP);
        }
        this.half = bisected.getHalf();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Bisected) blockData).setHalf(half);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new BisectedData(blockData);
    }
}
