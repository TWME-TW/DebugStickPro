package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Leaves;

public class LeavesPersistentData implements SubBlockData {
    private BlockData blockData;
    private boolean persistent;
    private boolean isUsing;

    public LeavesPersistentData(BlockData blockData) {
        this.blockData = blockData;
        this.persistent = ((Leaves) blockData).isPersistent();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.LeavesPersistentDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(persistent);
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
    public SubBlockData getDataFac(BlockData blockData) {
        return new LeavesPersistentData(blockData);
    }
}
