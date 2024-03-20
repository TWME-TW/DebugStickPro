package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.BubbleColumn;

public class BubbleColumnData implements SubBlockData {

    private final BlockData blockData;
    private boolean hasBubbleColumn;
    private boolean isUsing = false;

    public BubbleColumnData(BlockData blockData) {
        this.blockData = blockData;
        this.hasBubbleColumn = ((BubbleColumn) blockData).isDrag();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.BubbleColumnDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(hasBubbleColumn);
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
    public SubBlockData getDataFac(BlockData blockData) {
        return new BubbleColumnData(blockData);
    }
}
