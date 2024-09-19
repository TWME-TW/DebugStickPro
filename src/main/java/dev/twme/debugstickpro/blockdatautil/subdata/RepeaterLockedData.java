package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Repeater;

public class RepeaterLockedData extends SubBlockData {
    private boolean locked;

    public RepeaterLockedData(BlockData blockData) {
        this.blockData = blockData;
        this.locked = ((Repeater) blockData).isLocked();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.RepeaterLockedDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(locked);
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
    public SubBlockData fromBlockData(BlockData blockData) {
        return new RepeaterLockedData(blockData);
    }
}
