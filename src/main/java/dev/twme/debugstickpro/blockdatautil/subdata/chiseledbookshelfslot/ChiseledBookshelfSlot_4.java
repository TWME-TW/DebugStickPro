package dev.twme.debugstickpro.blockdatautil.subdata.chiseledbookshelfslot;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;

public class ChiseledBookshelfSlot_4 extends ChiseledBookshelfSlot {

    public ChiseledBookshelfSlot_4(BlockData blockData) {
        super(blockData, 4);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.ChiseledBookshelfSlot_4DataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new ChiseledBookshelfSlot_4(blockData);
    }
}
