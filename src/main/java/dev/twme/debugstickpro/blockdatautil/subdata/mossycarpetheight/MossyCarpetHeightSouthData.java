package dev.twme.debugstickpro.blockdatautil.subdata.mossycarpetheight;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class MossyCarpetHeightSouthData extends MossyCarpetHeightData {
    public MossyCarpetHeightSouthData(BlockData blockData) {
        super(blockData, BlockFace.SOUTH);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MossyCarpetHeightSouthDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MossyCarpetHeightSouthData(blockData);
    }
}
