package dev.twme.debugstickpro.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Segmentable;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;

public class SegmentableData extends SubBlockData {
    private int segmentAmount;

    public SegmentableData(BlockData blockData) {
        this.blockData = blockData;
        this.segmentAmount = ((Segmentable) blockData).getSegmentAmount();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.SegmentableDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(segmentAmount);
    }

    @Override
    public SubBlockData nextData() {
        Segmentable segmentable = ((Segmentable) blockData);
        if (segmentAmount >= segmentable.getMaximumSegmentAmount()) {
            segmentAmount = segmentable.getMinimumSegmentAmount();
        } else {
            segmentAmount++;
        }
        segmentable.setSegmentAmount(segmentAmount);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Segmentable segmentable = ((Segmentable) blockData);
        if (segmentAmount <= segmentable.getMinimumSegmentAmount()) {
            segmentAmount = segmentable.getMaximumSegmentAmount();
        } else {
            segmentAmount--;
        }
        segmentable.setSegmentAmount(segmentAmount);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Segmentable) blockData).setSegmentAmount(segmentAmount);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new SegmentableData(blockData);
    }
}
