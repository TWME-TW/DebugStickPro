package dev.twme.debugstickpro.blockdatautil.subdata.chiseledbookshelfslot;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;

public class ChiseledBookshelfSlot_0 extends ChiseledBookshelfSlot {

    public ChiseledBookshelfSlot_0(BlockData blockData) {
        super(blockData, 0);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.ChiseledBookshelfSlot_0DataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new ChiseledBookshelfSlot_0(blockData);
    }
}
