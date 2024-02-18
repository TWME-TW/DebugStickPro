package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.ChiseledBookshelf;

public class ChiseledBookshelfSlot_2 implements SubBlockData{
    private BlockData blockData;
    private boolean slot_2;
    private boolean isUsing = false;
    public ChiseledBookshelfSlot_2(BlockData blockData){
        this.blockData = blockData;
        this.slot_2 = ((ChiseledBookshelf) blockData).isSlotOccupied(2);
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
        return LangFile.ChiseledBookshelfSlot_2.replace("%data%", getDataAsString());
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(slot_2);
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
        if (chiseledBookshelf.isSlotOccupied(2)){
            chiseledBookshelf.setSlotOccupied(2, false);
        } else {
            chiseledBookshelf.setSlotOccupied(2, true);
        }
        this.slot_2 = chiseledBookshelf.isSlotOccupied(2);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((ChiseledBookshelf)blockData).setSlotOccupied(2, slot_2);
        return blockData;
    }
}
