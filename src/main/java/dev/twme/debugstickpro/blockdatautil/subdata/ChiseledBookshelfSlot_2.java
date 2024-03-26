package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.ChiseledBookshelf;

public class ChiseledBookshelfSlot_2 implements SubBlockData {
    private final BlockData blockData;
    private boolean slot_2;
    private boolean isUsing = false;

    public ChiseledBookshelfSlot_2(BlockData blockData) {
        this.blockData = blockData;
        this.slot_2 = ((ChiseledBookshelf) blockData).isSlotOccupied(2);
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.ChiseledBookshelfSlot_2DataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
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

    public SubBlockData nextData() {
        ChiseledBookshelf chiseledBookshelf = ((ChiseledBookshelf) blockData);
        chiseledBookshelf.setSlotOccupied(2, !chiseledBookshelf.isSlotOccupied(2));
        this.slot_2 = chiseledBookshelf.isSlotOccupied(2);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((ChiseledBookshelf) blockData).setSlotOccupied(2, slot_2);
        return blockData;
    }

    @Override
    public SubBlockData getDataFac(BlockData blockData) {
        return new ChiseledBookshelfSlot_2(blockData);
    }
}
