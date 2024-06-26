package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.ChiseledBookshelf;

public class ChiseledBookshelfSlot_5 implements SubBlockData {
    private final BlockData blockData;
    private boolean slot_5;
    private boolean isUsing = false;

    public ChiseledBookshelfSlot_5(BlockData blockData) {
        this.blockData = blockData;
        this.slot_5 = ((ChiseledBookshelf) blockData).isSlotOccupied(5);
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.ChiseledBookshelfSlot_5DataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(slot_5);
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

    @Override
    public SubBlockData nextData() {
        ChiseledBookshelf chiseledBookshelf = ((ChiseledBookshelf) blockData);
        chiseledBookshelf.setSlotOccupied(5, !chiseledBookshelf.isSlotOccupied(5));
        this.slot_5 = chiseledBookshelf.isSlotOccupied(5);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((ChiseledBookshelf) blockData).setSlotOccupied(5, slot_5);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new ChiseledBookshelfSlot_5(blockData);
    }
}
