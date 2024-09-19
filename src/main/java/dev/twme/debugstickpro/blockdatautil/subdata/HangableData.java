package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Hangable;

public class HangableData extends SubBlockData {
    private boolean hangable;

    public HangableData(BlockData blockData) {
        this.blockData = blockData;
        this.hangable = ((Hangable) blockData).isHanging();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.HangableDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(hangable);
    }

    @Override
    public SubBlockData nextData() {
        Hangable hangable = ((Hangable) blockData);
        hangable.setHanging(!hangable.isHanging());
        this.hangable = hangable.isHanging();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Hangable) blockData).setHanging(hangable);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new HangableData(blockData);
    }
}
