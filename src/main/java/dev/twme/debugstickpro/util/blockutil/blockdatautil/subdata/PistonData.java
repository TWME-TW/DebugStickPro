package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Piston;

public class PistonData implements SubBlockData{
    private String NAME = "Piston";
    private BlockData blockData;
    private boolean extended;
    public PistonData(BlockData blockData) {
        this.blockData = blockData;
        this.extended = ((Piston) blockData).isExtended();
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
        nextExtended();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Extended: " + extended;
    }

    @Override
    public String getNextAsString() {
        nextExtended();
        return "Extended: " + extended;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(extended);
    }

    @Override
    public String getNextDataAsString() {
        nextExtended();
        return String.valueOf(extended);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextExtended(){
        Piston piston = ((Piston) blockData);
        extended = !extended;
        piston.setExtended(extended);
    }
}
