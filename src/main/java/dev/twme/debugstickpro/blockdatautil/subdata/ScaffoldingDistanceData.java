package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Scaffolding;

public class ScaffoldingDistanceData implements SubBlockData {
    private BlockData blockData;
    private int distance;
    private boolean isUsing = false;

    public ScaffoldingDistanceData(BlockData blockData) {
        this.blockData = blockData;
        this.distance = ((Scaffolding) blockData).getDistance();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.ScaffoldingDistanceDataName;
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
    public BlockData copyTo(BlockData blockData) {
        return null;
    }
}