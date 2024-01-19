package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.ChiseledBookshelf;

public class ChiseledBookshelfSlot_3 implements SubBlockData{
    private String NAME = "Bookshelf Slot";
    private BlockData blockData;
    private boolean slot_3;
    public ChiseledBookshelfSlot_3(BlockData blockData){
        this.blockData = blockData;
        this.slot_3 = ((ChiseledBookshelf) blockData).isSlotOccupied(3);
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
        return "Bookshelf Slot 3: " + slot_3;
    }

    @Override
    public String getNextAsString() {
        nextSlotProperty();
        return "Bookshelf Slot 3: " + slot_3;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(slot_3);
    }

    @Override
    public String getNextDataAsString() {
        nextSlotProperty();
        return String.valueOf(slot_3);
    }

    private void nextSlotProperty(){
        ChiseledBookshelf chiseledBookshelf = ((ChiseledBookshelf) blockData);
        if (chiseledBookshelf.isSlotOccupied(3)){
            chiseledBookshelf.setSlotOccupied(3, false);
        } else {
            chiseledBookshelf.setSlotOccupied(3, true);
        }
        this.slot_3 = chiseledBookshelf.isSlotOccupied(3);
    }
}
