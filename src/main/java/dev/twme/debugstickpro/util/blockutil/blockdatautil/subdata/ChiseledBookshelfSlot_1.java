package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.ChiseledBookshelf;

public class ChiseledBookshelfSlot_1 implements SubBlockData{
    private BlockData blockData;
    private boolean slot_1;
    private boolean isUsing = false;
    public ChiseledBookshelfSlot_1(BlockData blockData){
        this.blockData = blockData;
        this.slot_1 = ((ChiseledBookshelf) blockData).isSlotOccupied(1);
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
        return LangFile.ChiseledBookshelfSlot_1.replace("%data%",getDataAsString()));
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(slot_1);
    }


    @Override
    public void setIsUsing(boolean isUsing) {
        this.isUsing = isUsing;
    }

    @Override
    public boolean isUsing() {
        return isUsing;
    }

    public SubBlockData nextData(){
        ChiseledBookshelf chiseledBookshelf = ((ChiseledBookshelf) blockData);
        if (chiseledBookshelf.isSlotOccupied(1)){
            chiseledBookshelf.setSlotOccupied(1, false);
        } else {
            chiseledBookshelf.setSlotOccupied(1, true);
        }
        this.slot_1 = chiseledBookshelf.isSlotOccupied(1);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((ChiseledBookshelf)blockData).setSlotOccupied(1, slot_1);
        return blockData;
    }
}
