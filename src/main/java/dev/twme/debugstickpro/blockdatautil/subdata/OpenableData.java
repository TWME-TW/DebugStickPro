package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Openable;

public class OpenableData extends SubBlockData {
    private boolean isOpen;

    public OpenableData(BlockData blockData) {
        this.blockData = blockData;
        this.isOpen = ((Openable) blockData).isOpen();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.OpenableDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(isOpen);
    }

    @Override
    public SubBlockData nextData() {
        Openable openable = ((Openable) blockData);
        openable.setOpen(!isOpen);
        this.isOpen = openable.isOpen();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Openable) blockData).setOpen(isOpen);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new OpenableData(blockData);
    }
}
