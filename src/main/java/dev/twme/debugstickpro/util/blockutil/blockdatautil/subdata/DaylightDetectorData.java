package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.DaylightDetector;

public class DaylightDetectorData implements SubBlockData{
    private String NAME = "Inverted";
    private BlockData blockData;
    private boolean inverted;
    public DaylightDetectorData(BlockData blockData){
        this.blockData = blockData;
        this.inverted = ((DaylightDetector) blockData).isInverted();
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
        nextInvertedProperty();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Inverted: " + inverted;
    }

    @Override
    public String getNextAsString() {
        nextInvertedProperty();
        return "Inverted: " + inverted;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(inverted);
    }

    @Override
    public String getNextDataAsString() {
        nextInvertedProperty();
        return String.valueOf(inverted);
    }

    private void nextInvertedProperty(){
        DaylightDetector daylightDetector = ((DaylightDetector) blockData);
        daylightDetector.setInverted(!daylightDetector.isInverted());
        this.inverted = daylightDetector.isInverted();
    }
}
