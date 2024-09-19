package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.Attachable;
import org.bukkit.block.data.BlockData;

public class AttachableData extends SubBlockData {
    private boolean isAttached;

    public AttachableData(BlockData blockData) {
        this.blockData = blockData;
        this.isAttached = ((Attachable) blockData).isAttached();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.AttachableDataName;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(isAttached);
    }


    @Override
    public SubBlockData nextData() {
        ((Attachable) blockData).setAttached(!isAttached);
        this.isAttached = ((Attachable) blockData).isAttached();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Attachable) blockData).setAttached(isAttached);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new AttachableData(blockData);
    }
}
