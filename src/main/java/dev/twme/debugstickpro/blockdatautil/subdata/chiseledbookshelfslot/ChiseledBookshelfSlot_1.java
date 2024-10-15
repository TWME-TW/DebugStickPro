package dev.twme.debugstickpro.blockdatautil.subdata.chiseledbookshelfslot;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;

public class ChiseledBookshelfSlot_1 extends ChiseledBookshelfSlot {

    public ChiseledBookshelfSlot_1(BlockData blockData) {
        super(blockData, 1);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.ChiseledBookshelfSlot_1DataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new ChiseledBookshelfSlot_1(blockData);
    }
}
