package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Leaves;

public class LeavesDistanceData implements SubBlockData {
    private String NAME = "Leaves Distance";
    private BlockData blockData;
    private int distance;
    private boolean isUsing;

    public LeavesDistanceData(BlockData blockData) {
        this.blockData = blockData;
        this.distance = ((Leaves) blockData).getDistance();
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String dataName() {
        return LangFile.LeavesDistanceDataName;
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
    public BlockData copyTo(BlockData blockData) {
        ((Leaves) blockData).setDistance(distance);
        return blockData;
    }
}
