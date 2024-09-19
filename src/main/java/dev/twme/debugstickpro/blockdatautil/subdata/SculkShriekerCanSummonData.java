package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.SculkShrieker;

public class SculkShriekerCanSummonData extends SubBlockData {
    private boolean isCanSummon;

    public SculkShriekerCanSummonData(BlockData blockData) {
        this.blockData = blockData;
        this.isCanSummon = ((SculkShrieker) blockData).isCanSummon();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.SculkShriekerCanSummonDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(isCanSummon);
    }

    public SubBlockData nextData() {
        SculkShrieker sculkShrieker = ((SculkShrieker) blockData);
        isCanSummon = !isCanSummon;
        sculkShrieker.setCanSummon(isCanSummon);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((SculkShrieker) blockData).setCanSummon(isCanSummon);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new SculkShriekerCanSummonData(blockData);
    }
}
