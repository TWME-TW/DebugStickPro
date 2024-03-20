package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.ChiseledBookshelf;

public class ChiseledBookshelfSlot_4 implements SubBlockData {
    private final BlockData blockData;
    private boolean slot_4;
    private boolean isUsing = false;

    public ChiseledBookshelfSlot_4(BlockData blockData) {
        this.blockData = blockData;
        this.slot_4 = ((ChiseledBookshelf) blockData).isSlotOccupied(4);
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.ChiseledBookshelfSlot_4DataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
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

    public SubBlockData nextData() {
        ChiseledBookshelf chiseledBookshelf = ((ChiseledBookshelf) blockData);
        chiseledBookshelf.setSlotOccupied(4, !chiseledBookshelf.isSlotOccupied(4));
        this.slot_4 = chiseledBookshelf.isSlotOccupied(4);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((ChiseledBookshelf) blockData).setSlotOccupied(4, slot_4);
        return blockData;
    }

    @Override
    public SubBlockData getDataFac(BlockData blockData) {
        return new ChiseledBookshelfSlot_4(blockData);
    }
}
