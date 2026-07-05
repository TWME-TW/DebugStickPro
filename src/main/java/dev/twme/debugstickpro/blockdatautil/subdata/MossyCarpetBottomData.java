package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.MossyCarpet;

public class MossyCarpetBottomData extends SubBlockData {
    private boolean bottom;

    public MossyCarpetBottomData(BlockData blockData) {
        this.blockData = blockData;
        this.bottom = ((MossyCarpet) blockData).isBottom();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MossyCarpetBottomDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(bottom);
    }

    @Override
    public SubBlockData nextData() {
        bottom = !bottom;
        ((MossyCarpet) blockData).setBottom(bottom);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((MossyCarpet) blockData).setBottom(bottom);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MossyCarpetBottomData(blockData);
    }
}
