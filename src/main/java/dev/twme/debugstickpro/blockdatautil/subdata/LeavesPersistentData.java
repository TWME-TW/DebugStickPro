package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Leaves;

public class LeavesPersistentData extends SubBlockData {
    private boolean persistent;

    public LeavesPersistentData(BlockData blockData) {
        this.blockData = blockData;
        this.persistent = ((Leaves) blockData).isPersistent();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.LeavesPersistentDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(persistent);
    }

    public SubBlockData nextData() {
        ((Leaves) blockData).setPersistent(!persistent);
        persistent = ((Leaves) blockData).isPersistent();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Leaves) blockData).setPersistent(persistent);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new LeavesPersistentData(blockData);
    }
}
