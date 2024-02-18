package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.DaylightDetector;

public class DaylightDetectorData implements SubBlockData{
    private String NAME = "Inverted";
    private BlockData blockData;
    private boolean inverted;
    private boolean isUsing = false;
    public DaylightDetectorData(BlockData blockData){
        this.blockData = blockData;
        this.inverted = ((DaylightDetector) blockData).isInverted();
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
        return "Inverted: " + inverted;
    }



    @Override
    public String getDataAsString() {
        return String.valueOf(inverted);
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

    public SubBlockData nextData(){
        DaylightDetector daylightDetector = ((DaylightDetector) blockData);
        daylightDetector.setInverted(!daylightDetector.isInverted());
        this.inverted = daylightDetector.isInverted();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((DaylightDetector) blockData).setInverted(inverted);
        return blockData;
    }
}
