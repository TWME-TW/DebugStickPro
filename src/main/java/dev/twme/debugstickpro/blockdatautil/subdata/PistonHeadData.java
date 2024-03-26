package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.PistonHead;

public class PistonHeadData implements SubBlockData {
    private final BlockData blockData;
    private boolean shortArm;
    private boolean isUsing = false;

    public PistonHeadData(BlockData blockData) {
        this.blockData = blockData;
        this.shortArm = ((PistonHead) blockData).isShort();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.PistonHeadDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(shortArm);
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
        PistonHead pistonHead = ((PistonHead) blockData);
        shortArm = !shortArm;
        pistonHead.setShort(shortArm);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((PistonHead) blockData).setShort(shortArm);
        return blockData;
    }

    @Override
    public SubBlockData getDataFac(BlockData blockData) {
        return new PistonHeadData(blockData);
    }
}
