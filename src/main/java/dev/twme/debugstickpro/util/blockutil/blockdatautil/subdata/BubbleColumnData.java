package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Brushable;
import org.bukkit.block.data.type.BubbleColumn;

public class BubbleColumnData implements SubBlockData{
    private String NAME = "BubbleColumn";
    private BlockData blockData;
    private boolean hasBubbleColumn;
    public BubbleColumnData(BlockData blockData){
        this.blockData = blockData;
        this.hasBubbleColumn = ((BubbleColumn) blockData).isDrag();
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
    public BlockData getNextData() {
        nextDragProperty();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "BubbleColumn: " + hasBubbleColumn;
    }

    @Override
    public String getNextAsString() {
        nextDragProperty();
        return "BubbleColumn: " + hasBubbleColumn;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(hasBubbleColumn);
    }

    @Override
    public String getNextDataAsString() {
        nextDragProperty();
        return String.valueOf(hasBubbleColumn);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextDragProperty(){
        BubbleColumn bubbleColumn = ((BubbleColumn) blockData);
        bubbleColumn.setDrag(!bubbleColumn.isDrag());
        this.hasBubbleColumn = bubbleColumn.isDrag();
    }
}
