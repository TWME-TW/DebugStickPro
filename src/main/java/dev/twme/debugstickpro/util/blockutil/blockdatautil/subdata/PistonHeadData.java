package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.PistonHead;

public class PistonHeadData implements SubBlockData{
    private String NAME = "PistonHead";
    private BlockData blockData;
    private boolean shortArm;
    public PistonHeadData(BlockData blockData) {
        this.blockData = blockData;
        this.shortArm = ((PistonHead) blockData).isShort();
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
        nextShortArm();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "ShortArm: " + shortArm;
    }

    @Override
    public String getNextAsString() {
        nextShortArm();
        return "ShortArm: " + shortArm;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(shortArm);
    }

    @Override
    public String getNextDataAsString() {
        nextShortArm();
        return String.valueOf(shortArm);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextShortArm(){
        PistonHead pistonHead = ((PistonHead) blockData);
        shortArm = !shortArm;
        pistonHead.setShort(shortArm);
    }
}
