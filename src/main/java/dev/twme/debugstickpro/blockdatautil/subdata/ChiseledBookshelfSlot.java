package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.ChiseledBookshelf;

public abstract class ChiseledBookshelfSlot extends SubBlockData {
    private final int slotNumber;
    private boolean slotOccupied;

    public ChiseledBookshelfSlot(BlockData blockData, int slotNumber) {
        this.blockData = blockData;
        this.slotNumber = slotNumber;
        this.slotOccupied = ((ChiseledBookshelf) blockData).isSlotOccupied(slotNumber);
    }

    public String getDataAsString() {
        return String.valueOf(slotOccupied);
    }

    public SubBlockData nextData() {
        ChiseledBookshelf chiseledBookshelf = ((ChiseledBookshelf) blockData);
        chiseledBookshelf.setSlotOccupied(slotNumber, !chiseledBookshelf.isSlotOccupied(slotNumber));
        this.slotOccupied = chiseledBookshelf.isSlotOccupied(slotNumber);
        return this;
    }

    public SubBlockData previousData() {
        return nextData();
    }

    public BlockData copyTo(BlockData blockData) {
        ((ChiseledBookshelf) blockData).setSlotOccupied(slotNumber, slotOccupied);
        return blockData;
    }
}
