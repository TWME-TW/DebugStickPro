package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Bamboo;

public class BambooData implements SubBlockData {
    private BlockData blockData;
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
    public BlockData getData() {
        return blockData;
    }


    @Override
    public String getAsString() {
        return LangFile.Bamboo.replace("%data%", getDataAsString());
    }


    @Override
    public String getDataAsString() {
        return leaves.name();
    }


    @Override
    public void setIsUsing(boolean isUsing) {
        this.isUsing = isUsing;
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
    public BlockData copyTo(BlockData blockData) {
        ((Bamboo) blockData).setLeaves(leaves);
        return blockData;
    }
}
