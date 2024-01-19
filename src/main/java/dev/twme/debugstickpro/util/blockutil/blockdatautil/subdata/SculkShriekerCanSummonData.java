package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.SculkShrieker;

public class SculkShriekerCanSummonData implements SubBlockData{
    private String NAME = "SculkShriekerCanSummon";
    private BlockData blockData;
    private boolean isCanSummon;
    public SculkShriekerCanSummonData(BlockData blockData){
        this.blockData = blockData;
        this.isCanSummon = ((SculkShrieker) blockData).isCanSummon();
    }
    @Override
    public String NAME() {
        return NAME;
    }

    @Override
    public BlockData getData() {
        return blockData;
    }

    @Override
    public BlockData getNextData() {
        nextData();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "CanSummon: " + isCanSummon;
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "CanSummon: " + isCanSummon;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(isCanSummon);
    }

    @Override
    public String getNextDataAsString() {
        return String.valueOf(isCanSummon);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData(){
        SculkShrieker sculkShrieker = ((SculkShrieker) blockData);
        isCanSummon = !isCanSummon;
        sculkShrieker.setCanSummon(isCanSummon);
    }
}
