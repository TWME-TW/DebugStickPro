package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Leaves;

public class LeavesDistanceData extends SubBlockData {
    private int distance;

    public LeavesDistanceData(BlockData blockData) {
        this.blockData = blockData;
        this.distance = ((Leaves) blockData).getDistance();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.LeavesDistanceDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(distance);
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
