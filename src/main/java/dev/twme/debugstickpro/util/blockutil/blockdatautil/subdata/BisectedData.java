package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.BlockData;

public class BisectedData implements SubBlockData {

    private BlockData blockData;
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
    public BlockData getData() {
        return blockData;
    }


    @Override
    public String getAsString() {
        return LangFile.Bisected.replace("%data%", getDataAsString());
    }


    @Override
    public String getDataAsString() {
        return half.name();
    }


    @Override
    public void setIsUsing(boolean isUsing) {
        this.isUsing = isUsing;
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
    public BlockData copyTo(BlockData blockData) {
        ((Bisected) blockData).setHalf(half);
        return blockData;
    }
}
