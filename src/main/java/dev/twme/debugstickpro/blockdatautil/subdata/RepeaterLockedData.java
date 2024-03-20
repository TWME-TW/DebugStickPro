package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Repeater;

public class RepeaterLockedData implements SubBlockData {
    private final BlockData blockData;
    private boolean locked;
    private boolean isUsing = false;

    public RepeaterLockedData(BlockData blockData) {
        this.blockData = blockData;
        this.locked = ((Repeater) blockData).isLocked();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.RepeaterLockedDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(locked);
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
        Repeater repeater = ((Repeater) blockData);
        locked = !locked;
        repeater.setLocked(locked);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Repeater) blockData).setLocked(locked);
        return blockData;
    }

    @Override
    public SubBlockData getDataFac(BlockData blockData) {
        return new RepeaterLockedData(blockData);
    }
}
