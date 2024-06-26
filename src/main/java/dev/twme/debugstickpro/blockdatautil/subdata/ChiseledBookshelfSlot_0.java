package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.ChiseledBookshelf;

public class ChiseledBookshelfSlot_0 implements SubBlockData {
    private final BlockData blockData;
    private boolean slot_0;
    private boolean isUsing = false;

    public ChiseledBookshelfSlot_0(BlockData blockData) {
        this.blockData = blockData;
        this.slot_0 = ((ChiseledBookshelf) blockData).isSlotOccupied(0);
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.ChiseledBookshelfSlot_0DataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(slot_0);
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

    public SubBlockData nextData() {
        ChiseledBookshelf chiseledBookshelf = ((ChiseledBookshelf) blockData);
        chiseledBookshelf.setSlotOccupied(0, !chiseledBookshelf.isSlotOccupied(0));
        this.slot_0 = chiseledBookshelf.isSlotOccupied(0);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((ChiseledBookshelf) blockData).setSlotOccupied(0, slot_0);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new ChiseledBookshelfSlot_0(blockData);
    }
}
