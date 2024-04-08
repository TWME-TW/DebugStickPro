package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Leaves;

public class LeavesDistanceData implements SubBlockData {
    private final BlockData blockData;
    private int distance;
    private boolean isUsing;

    public LeavesDistanceData(BlockData blockData) {
        this.blockData = blockData;
        this.distance = ((Leaves) blockData).getDistance();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.LeavesDistanceDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(distance);
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
        Leaves leaves = (Leaves) blockData;
        if (distance >= leaves.getMaximumDistance()) {
            distance = leaves.getMinimumDistance();
        } else {
            distance++;
        }
        leaves.setDistance(distance);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Leaves leaves = (Leaves) blockData;
        if (distance <= leaves.getMinimumDistance()) {
            distance = leaves.getMaximumDistance();
        } else {
            distance--;
        }
        leaves.setDistance(distance);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Leaves) blockData).setDistance(distance);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new LeavesDistanceData(blockData);
    }
}
