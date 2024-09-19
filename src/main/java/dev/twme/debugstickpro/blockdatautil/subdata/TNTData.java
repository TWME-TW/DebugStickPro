package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.TNT;

public class TNTData extends SubBlockData {
    private boolean unstable;

    public TNTData(BlockData blockData) {
        this.blockData = blockData;
        this.unstable = ((TNT) blockData).isUnstable();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.TNTDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(unstable);
    }

    public SubBlockData nextData() {
        TNT tnt = ((TNT) blockData);
        unstable = !unstable;
        tnt.setUnstable(unstable);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((TNT) blockData).setUnstable(unstable);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new TNTData(blockData);
    }
}
