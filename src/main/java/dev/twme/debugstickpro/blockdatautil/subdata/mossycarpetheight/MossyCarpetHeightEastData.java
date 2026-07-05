package dev.twme.debugstickpro.blockdatautil.subdata.mossycarpetheight;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class MossyCarpetHeightEastData extends MossyCarpetHeightData {
    public MossyCarpetHeightEastData(BlockData blockData) {
        super(blockData, BlockFace.EAST);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MossyCarpetHeightEastDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MossyCarpetHeightEastData(blockData);
    }
}
