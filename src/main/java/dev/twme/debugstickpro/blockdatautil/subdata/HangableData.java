package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Hangable;

public class HangableData implements SubBlockData {
    private BlockData blockData;
    private boolean hangable;
    private boolean isUsing = false;

    public HangableData(BlockData blockData) {
        this.blockData = blockData;
        this.hangable = ((Hangable) blockData).isHanging();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.HangableDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(hangable);
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
        Hangable hangable = ((Hangable) blockData);
        hangable.setHanging(!hangable.isHanging());
        this.hangable = hangable.isHanging();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Hangable) blockData).setHanging(hangable);
        return blockData;
    }
}
