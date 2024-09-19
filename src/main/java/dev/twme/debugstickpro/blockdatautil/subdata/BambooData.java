package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Bamboo;

public class BambooData extends SubBlockData {
    private Bamboo.Leaves leaves;

    public BambooData(BlockData blockData) {
        this.blockData = blockData;
        this.leaves = ((Bamboo) blockData).getLeaves();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.BambooDataName;
    }


    @Override
    public String getDataAsString() {
        return leaves.name();
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
    public SubBlockData fromBlockData(BlockData blockData) {
        return new BambooData(blockData);
    }
}
