package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.ChiseledBookshelf;

public class ChiseledBookshelfSlot_5 implements SubBlockData{
    private String NAME = "Bookshelf Slot";
    private BlockData blockData;
    private boolean slot_5;
    public ChiseledBookshelfSlot_5(BlockData blockData){
        this.blockData = blockData;
        this.slot_5 = ((ChiseledBookshelf) blockData).isSlotOccupied(5);
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
        nextSlotProperty();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Slot 5: " + slot_5;
    }

    @Override
    public String getNextAsString() {
        nextSlotProperty();
        return "Slot 5: " + slot_5;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(slot_5);
    }

    @Override
    public String getNextDataAsString() {
        nextSlotProperty();
        return String.valueOf(slot_5);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextSlotProperty(){
        ChiseledBookshelf chiseledBookshelf = ((ChiseledBookshelf) blockData);
        if (chiseledBookshelf.isSlotOccupied(5)){
            chiseledBookshelf.setSlotOccupied(5, false);
        } else {
            chiseledBookshelf.setSlotOccupied(5, true);
        }
        this.slot_5 = chiseledBookshelf.isSlotOccupied(5);
    }
}
