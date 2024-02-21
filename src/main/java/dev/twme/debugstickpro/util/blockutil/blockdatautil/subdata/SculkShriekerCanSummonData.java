package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.SculkShrieker;

public class SculkShriekerCanSummonData implements SubBlockData {
    private BlockData blockData;
    private boolean isCanSummon;
    private boolean isUsing = false;

    public SculkShriekerCanSummonData(BlockData blockData) {
        this.blockData = blockData;
        this.isCanSummon = ((SculkShrieker) blockData).isCanSummon();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.SculkShriekerCanSummonDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(isCanSummon);
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

    public SubBlockData nextData() {
        SculkShrieker sculkShrieker = ((SculkShrieker) blockData);
        isCanSummon = !isCanSummon;
        sculkShrieker.setCanSummon(isCanSummon);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((SculkShrieker) blockData).setCanSummon(isCanSummon);
        return blockData;
    }
}
