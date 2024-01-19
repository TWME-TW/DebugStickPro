package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.ChiseledBookshelf;

public class ChiseledBookshelfSlot_4 implements SubBlockData{
    private String NAME = "Bookshelf Slot";
    private BlockData blockData;
    private boolean slot_4;
    public ChiseledBookshelfSlot_4(BlockData blockData){
        this.blockData = blockData;
        this.slot_4 = ((ChiseledBookshelf) blockData).isSlotOccupied(4);
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
        return "Slot 4: " + slot_4;
    }

    @Override
    public String getNextAsString() {
        nextSlotProperty();
        return "Slot 4: " + slot_4;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(slot_4);
    }

    @Override
    public String getNextDataAsString() {
        nextSlotProperty();
        return String.valueOf(slot_4);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextSlotProperty(){
        ChiseledBookshelf chiseledBookshelf = ((ChiseledBookshelf) blockData);
        if (chiseledBookshelf.isSlotOccupied(4)){
            chiseledBookshelf.setSlotOccupied(4, false);
        } else {
            chiseledBookshelf.setSlotOccupied(4, true);
        }
        this.slot_4 = chiseledBookshelf.isSlotOccupied(4);
    }
}
