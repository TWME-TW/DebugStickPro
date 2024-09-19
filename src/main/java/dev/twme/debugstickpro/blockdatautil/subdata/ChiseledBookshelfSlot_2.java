package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.ChiseledBookshelf;

public class ChiseledBookshelfSlot_2 extends ChiseledBookshelfSlot {

    public ChiseledBookshelfSlot_2(BlockData blockData) {
        super(blockData, 2);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.ChiseledBookshelfSlot_2DataName;
    }


    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new ChiseledBookshelfSlot_2(blockData);
    }
}
