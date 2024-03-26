package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Bamboo;

public class BambooData implements SubBlockData {
    private final BlockData blockData;
    private Bamboo.Leaves leaves;
    private boolean isUsing = false;

    public BambooData(BlockData blockData) {
        this.blockData = blockData;
        this.leaves = ((Bamboo) blockData).getLeaves();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.BambooDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return leaves.name();
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
        Bamboo bamboo = (Bamboo) blockData;
        if (bamboo.getLeaves() == Bamboo.Leaves.NONE) {
            bamboo.setLeaves(Bamboo.Leaves.SMALL);
        } else if (bamboo.getLeaves() == Bamboo.Leaves.SMALL) {
            bamboo.setLeaves(Bamboo.Leaves.LARGE);
        } else if (bamboo.getLeaves() == Bamboo.Leaves.LARGE) {
            bamboo.setLeaves(Bamboo.Leaves.NONE);
        }
        this.leaves = bamboo.getLeaves();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Bamboo bamboo = (Bamboo) blockData;
        if (bamboo.getLeaves() == Bamboo.Leaves.NONE) {
            bamboo.setLeaves(Bamboo.Leaves.LARGE);
        } else if (bamboo.getLeaves() == Bamboo.Leaves.LARGE) {
            bamboo.setLeaves(Bamboo.Leaves.SMALL);
        } else if (bamboo.getLeaves() == Bamboo.Leaves.SMALL) {
            bamboo.setLeaves(Bamboo.Leaves.NONE);
        }
        this.leaves = bamboo.getLeaves();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Bamboo) blockData).setLeaves(leaves);
        return blockData;
    }

    @Override
    public SubBlockData getDataFac(BlockData blockData) {
        return new BambooData(blockData);
    }
}
