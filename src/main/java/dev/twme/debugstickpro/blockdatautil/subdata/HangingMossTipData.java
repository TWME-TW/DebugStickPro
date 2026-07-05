package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.HangingMoss;

public class HangingMossTipData extends SubBlockData {
    private boolean tip;

    public HangingMossTipData(BlockData blockData) {
        this.blockData = blockData;
        this.tip = ((HangingMoss) blockData).isTip();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.HangingMossTipDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(tip);
    }

    @Override
    public SubBlockData nextData() {
        tip = !tip;
        ((HangingMoss) blockData).setTip(tip);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((HangingMoss) blockData).setTip(tip);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new HangingMossTipData(blockData);
    }
}
