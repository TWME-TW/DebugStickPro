package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.ChiseledBookshelf;

public class ChiseledBookshelfSlot_5 extends ChiseledBookshelfSlot {

    public ChiseledBookshelfSlot_5(BlockData blockData) {
        super(blockData, 5);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.ChiseledBookshelfSlot_5DataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new ChiseledBookshelfSlot_5(blockData);
    }
}
