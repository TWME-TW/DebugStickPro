package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.ChiseledBookshelf;

public class ChiseledBookshelfSlot_1 implements SubBlockData{
    private String NAME = "Bookshelf Slot";
    private BlockData blockData;
    private boolean slot_1;
    public ChiseledBookshelfSlot_1(BlockData blockData){
        this.blockData = blockData;
        this.slot_1 = ((ChiseledBookshelf) blockData).isSlotOccupied(1);
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
        return "Slot 1: " + slot_1;
    }

    @Override
    public String getNextAsString() {
        nextSlotProperty();
        return "Slot 1: " + slot_1;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(slot_1);
    }

    @Override
    public String getNextDataAsString() {
        nextSlotProperty();
        return String.valueOf(slot_1);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextSlotProperty(){
        ChiseledBookshelf chiseledBookshelf = ((ChiseledBookshelf) blockData);
        if (chiseledBookshelf.isSlotOccupied(1)){
            chiseledBookshelf.setSlotOccupied(1, false);
        } else {
            chiseledBookshelf.setSlotOccupied(1, true);
        }
        this.slot_1 = chiseledBookshelf.isSlotOccupied(1);
    }
}
