package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.BubbleColumn;

public class BubbleColumnData extends SubBlockData {
    private boolean hasBubbleColumn;

    public BubbleColumnData(BlockData blockData) {
        this.blockData = blockData;
        this.hasBubbleColumn = ((BubbleColumn) blockData).isDrag();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.BubbleColumnDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(hasBubbleColumn);
    }

    public SubBlockData nextData() {
        BubbleColumn bubbleColumn = ((BubbleColumn) blockData);
        bubbleColumn.setDrag(!bubbleColumn.isDrag());
        this.hasBubbleColumn = bubbleColumn.isDrag();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((BubbleColumn) blockData).setDrag(hasBubbleColumn);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new BubbleColumnData(blockData);
    }
}
