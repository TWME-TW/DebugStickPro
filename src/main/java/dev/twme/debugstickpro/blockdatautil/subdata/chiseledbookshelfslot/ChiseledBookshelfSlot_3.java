package dev.twme.debugstickpro.blockdatautil.subdata.chiseledbookshelfslot;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;

public class ChiseledBookshelfSlot_3 extends ChiseledBookshelfSlot {

    public ChiseledBookshelfSlot_3(BlockData blockData) {
        super(blockData, 3);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.ChiseledBookshelfSlot_3DataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new ChiseledBookshelfSlot_3(blockData);
    }
}
