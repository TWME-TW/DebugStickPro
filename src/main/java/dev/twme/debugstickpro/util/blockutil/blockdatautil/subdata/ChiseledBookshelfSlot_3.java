package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.ChiseledBookshelf;

public class ChiseledBookshelfSlot_3 implements SubBlockData{
    private BlockData blockData;
    private boolean slot_3;
    private boolean isUsing = false;
    public ChiseledBookshelfSlot_3(BlockData blockData){
        this.blockData = blockData;
        this.slot_3 = ((ChiseledBookshelf) blockData).isSlotOccupied(3);
    }
    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public BlockData getData() {
        return blockData;
    }


    @Override
    public String getAsString() {
        return "Slot 3: " + slot_3;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(slot_3);
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
        if (chiseledBookshelf.isSlotOccupied(3)){
            chiseledBookshelf.setSlotOccupied(3, false);
        } else {
            chiseledBookshelf.setSlotOccupied(3, true);
        }
        this.slot_3 = chiseledBookshelf.isSlotOccupied(3);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        return null;
    }
}
