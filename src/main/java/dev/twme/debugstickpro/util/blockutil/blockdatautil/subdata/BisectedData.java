package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.BlockData;

public class BisectedData implements SubBlockData{
    private String NAME = "Bisected";
    private BlockData blockData;
    private Bisected.Half half;

    public BisectedData(BlockData blockData) {
        this.blockData = blockData;
        this.half = ((Bisected) blockData).getHalf();
    }
    @Override
    public String NAME() {
        return NAME();
    }

    @Override
    public BlockData getData() {
        return blockData;
    }

    @Override
    public BlockData getNextData() {
        nextHalf();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Half: " + half.name();
    }

    @Override
    public String getNextAsString() {
        nextHalf();
        return "Half: " + half.name();
    }

    @Override
    public String getDataAsString() {
        return half.name();
    }

    @Override
    public String getNextDataAsString() {
        nextHalf();
        return half.name();
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextHalf(){
        Bisected bisected = ((Bisected) blockData);
        if (bisected.getHalf() == Bisected.Half.TOP) {
            bisected.setHalf(Bisected.Half.BOTTOM);
        } else {
            bisected.setHalf(Bisected.Half.TOP);
        }
        this.half = bisected.getHalf();
    }
}
