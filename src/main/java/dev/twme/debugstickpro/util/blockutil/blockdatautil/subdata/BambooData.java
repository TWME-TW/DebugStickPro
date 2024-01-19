package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Bamboo;

public class BambooData implements SubBlockData{
    private final String NAME = "BambooLeaves";
    private BlockData blockData;
    private Bamboo.Leaves leaves;

    public BambooData(BlockData blockData) {
        this.blockData = blockData;
        this.leaves = ((Bamboo) blockData).getLeaves();
    }
    @Override
    public String NAME() {
        return NAME;
    }

    @Override
    public BlockData getData() {
        return blockData;
    }

    @Override
    public BlockData getNextData() {
        nextLeaveType();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "BambooLeaves: " + leaves;
    }

    @Override
    public String getNextAsString() {
        nextLeaveType();
        return "BambooLeaves: " + leaves;
    }

    @Override
    public String getDataAsString(BlockData blockData) {
        return leaves.name();
    }

    @Override
    public String getNextDataAsString() {
        nextLeaveType();
        return leaves.name();
    }

    private void nextLeaveType() {
        Bamboo bamboo = (Bamboo) blockData;
        if (bamboo.getLeaves() == Bamboo.Leaves.NONE) {
            bamboo.setLeaves(Bamboo.Leaves.SMALL);
        } else if (bamboo.getLeaves() == Bamboo.Leaves.SMALL) {
            bamboo.setLeaves(Bamboo.Leaves.LARGE);
        } else if (bamboo.getLeaves() == Bamboo.Leaves.LARGE) {
            bamboo.setLeaves(Bamboo.Leaves.NONE);
        }
        this.leaves = bamboo.getLeaves();
    }
}
