package dev.twme.debugstickpro.blockdatautil.subdata.mossycarpetheight;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class MossyCarpetHeightNorthData extends MossyCarpetHeightData {
    public MossyCarpetHeightNorthData(BlockData blockData) {
        super(blockData, BlockFace.NORTH);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MossyCarpetHeightNorthDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MossyCarpetHeightNorthData(blockData);
    }
}
