package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.PistonHead;

public class PistonHeadData implements SubBlockData{
    private String NAME = "PistonHead";
    private BlockData blockData;
    private boolean shortArm;
    private boolean isUsing = false;
    public PistonHeadData(BlockData blockData) {
        this.blockData = blockData;
        this.shortArm = ((PistonHead) blockData).isShort();
    }
    @Override
    public String name() {
        return NAME;
    }

    @Override
    public BlockData getData() {
        return blockData;
    }


    @Override
    public String getAsString() {
        return "ShortArm: " + shortArm;
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
    public SubBlockData nextData(){
        PistonHead pistonHead = ((PistonHead) blockData);
        shortArm = !shortArm;
        pistonHead.setShort(shortArm);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((PistonHead)blockData).setShort(shortArm);
        return blockData;
    }
}
