package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.ChiseledBookshelf;

public class ChiseledBookshelfSlot_4 implements SubBlockData{
    private String NAME = "Bookshelf Slot";
    private BlockData blockData;
    private boolean slot_4;
    private boolean isUsing = false;
    public ChiseledBookshelfSlot_4(BlockData blockData){
        this.blockData = blockData;
        this.slot_4 = ((ChiseledBookshelf) blockData).isSlotOccupied(4);
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
    public String getAsString() {
        return "Slot 4: " + slot_4;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(slot_4);
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

    public SubBlockData nextData(){
        ChiseledBookshelf chiseledBookshelf = ((ChiseledBookshelf) blockData);
        if (chiseledBookshelf.isSlotOccupied(4)){
            chiseledBookshelf.setSlotOccupied(4, false);
        } else {
            chiseledBookshelf.setSlotOccupied(4, true);
        }
        this.slot_4 = chiseledBookshelf.isSlotOccupied(4);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((ChiseledBookshelf) blockData) .setSlotOccupied(4,slot_4);
        return blockData;
    }
}
