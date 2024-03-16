package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.TNT;

public class TNTData implements SubBlockData {
    private BlockData blockData;
    private boolean unstable;
    private boolean isUsing = false;

    public TNTData(BlockData blockData) {
        this.blockData = blockData;
        this.unstable = ((TNT) blockData).isUnstable();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.TNTDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(unstable);
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
        TNT tnt = ((TNT) blockData);
        unstable = !unstable;
        tnt.setUnstable(unstable);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((TNT) blockData).setUnstable(unstable);
        return blockData;
    }
}
