package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.DaylightDetector;

public class DaylightDetectorData extends SubBlockData {
    private boolean inverted;

    public DaylightDetectorData(BlockData blockData) {
        this.blockData = blockData;
        this.inverted = ((DaylightDetector) blockData).isInverted();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.DaylightDetectorDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(inverted);
    }

    public SubBlockData nextData() {
        DaylightDetector daylightDetector = ((DaylightDetector) blockData);
        daylightDetector.setInverted(!daylightDetector.isInverted());
        this.inverted = daylightDetector.isInverted();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((DaylightDetector) blockData).setInverted(inverted);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new DaylightDetectorData(blockData);
    }
}
