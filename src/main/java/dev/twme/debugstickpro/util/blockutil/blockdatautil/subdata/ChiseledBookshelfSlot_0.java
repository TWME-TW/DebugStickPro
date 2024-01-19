package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.ChiseledBookshelf;

public class ChiseledBookshelfSlot_0 implements SubBlockData{
    private String NAME = "Bookshelf Slot";
    private BlockData blockData;
    private boolean slot_0;
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
        return "Slot 0: " + slot_0;
    }

    @Override
    public String getNextAsString() {
        nextSlotProperty();
        return "Slot 0: " + slot_0;
    }

    @Override
    public String getDataAsString(BlockData blockData) {
        return String.valueOf(slot_0);
    }

    @Override
    public String getNextDataAsString() {
        nextSlotProperty();
        return String.valueOf(slot_0);
    }

    private void nextSlotProperty(){
        ChiseledBookshelf chiseledBookshelf = ((ChiseledBookshelf) blockData);
        if (chiseledBookshelf.isSlotOccupied(0)){
            chiseledBookshelf.setSlotOccupied(0, false);
        } else {
            chiseledBookshelf.setSlotOccupied(0, true);
        }
        this.slot_0 = chiseledBookshelf.isSlotOccupied(0);
    }
}
