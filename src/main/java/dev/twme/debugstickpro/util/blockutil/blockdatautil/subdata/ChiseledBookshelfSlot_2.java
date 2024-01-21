package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.ChiseledBookshelf;

public class ChiseledBookshelfSlot_2 implements SubBlockData{
    private String NAME = "Bookshelf Slot";
    private BlockData blockData;
    private boolean slot_2;
    public ChiseledBookshelfSlot_2(BlockData blockData){
        this.blockData = blockData;
        this.slot_2 = ((ChiseledBookshelf) blockData).isSlotOccupied(2);
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
        nextSlotProperty();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Slot 2: " + slot_2;
    }

    @Override
    public String getNextAsString() {
        nextSlotProperty();
        return "Slot 2: " + slot_2;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(slot_2);
    }

    @Override
    public String getNextDataAsString() {
        nextSlotProperty();
        return String.valueOf(slot_2);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextSlotProperty(){
        ChiseledBookshelf chiseledBookshelf = ((ChiseledBookshelf) blockData);
        if (chiseledBookshelf.isSlotOccupied(2)){
            chiseledBookshelf.setSlotOccupied(2, false);
        } else {
            chiseledBookshelf.setSlotOccupied(2, true);
        }
        this.slot_2 = chiseledBookshelf.isSlotOccupied(2);
    }
}
