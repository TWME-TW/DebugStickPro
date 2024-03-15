package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.Attachable;
import org.bukkit.block.data.BlockData;

public class AttachableData implements SubBlockData {

    private BlockData blockData;
    private boolean isAttached;
    private boolean isUsing = false;


    public AttachableData(BlockData blockData) {
        this.blockData = blockData;
        this.isAttached = ((Attachable) blockData).isAttached();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.AttachableDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }



    @Override
    public String getDataAsString() {
        return String.valueOf(isAttached);
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
        ((Attachable) blockData).setAttached(!isAttached);
        this.isAttached = ((Attachable) blockData).isAttached();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Attachable) blockData).setAttached(isAttached);
        return blockData;
    }
}
