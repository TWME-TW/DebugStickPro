package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Openable;

public class OpenableData implements SubBlockData {

    private BlockData blockData;
    private boolean isOpen;
    private boolean isUsing = false;

    public OpenableData(BlockData blockData) {
        this.blockData = blockData;
        this.isOpen = ((Openable) blockData).isOpen();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.OpenableDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(isOpen);
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
