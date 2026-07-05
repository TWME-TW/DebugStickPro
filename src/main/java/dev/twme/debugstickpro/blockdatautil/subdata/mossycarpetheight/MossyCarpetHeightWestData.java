package dev.twme.debugstickpro.blockdatautil.subdata.mossycarpetheight;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class MossyCarpetHeightWestData extends MossyCarpetHeightData {
    public MossyCarpetHeightWestData(BlockData blockData) {
        super(blockData, BlockFace.WEST);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MossyCarpetHeightWestDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MossyCarpetHeightWestData(blockData);
    }
}
