package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.DaylightDetector;

public class DaylightDetectorData implements SubBlockData {
    private BlockData blockData;
    private boolean inverted;
    private boolean isUsing = false;

    public DaylightDetectorData(BlockData blockData) {
        this.blockData = blockData;
        this.inverted = ((DaylightDetector) blockData).isInverted();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DaylightDetectorDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
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

    public SubBlockData nextData() {
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
