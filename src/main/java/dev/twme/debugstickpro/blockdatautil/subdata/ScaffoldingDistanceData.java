package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Scaffolding;

public class ScaffoldingDistanceData extends SubBlockData {
    private int distance;

    public ScaffoldingDistanceData(BlockData blockData) {
        this.blockData = blockData;
        this.distance = ((Scaffolding) blockData).getDistance();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.ScaffoldingDistanceDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(distance);
    }

    @Override
    public SubBlockData nextData() {
        Scaffolding scaffolding = ((Scaffolding) blockData);
        if (distance >= scaffolding.getMaximumDistance()) {
            distance = 0;
        } else {
            distance++;
        }
        scaffolding.setDistance(distance);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Scaffolding scaffolding = ((Scaffolding) blockData);
        if (distance <= 0) {
            distance = scaffolding.getMaximumDistance();
        } else {
            distance--;
        }
        scaffolding.setDistance(distance);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Scaffolding) blockData).setDistance(distance);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new ScaffoldingDistanceData(blockData);
    }
}
