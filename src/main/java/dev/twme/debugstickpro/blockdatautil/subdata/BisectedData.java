package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.BlockData;

public class BisectedData implements SubBlockData {

    private final BlockData blockData;
    private Bisected.Half half;
    private boolean isUsing = false;

    public BisectedData(BlockData blockData) {
        this.blockData = blockData;
        this.half = ((Bisected) blockData).getHalf();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.BisectedDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return half.name();
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
    public SubBlockData getDataFac(BlockData blockData) {
        return new BisectedData(blockData);
    }
}
