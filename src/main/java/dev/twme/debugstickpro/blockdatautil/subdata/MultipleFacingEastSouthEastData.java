package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class MultipleFacingEastSouthEastData extends MultipleFacingData {

    public MultipleFacingEastSouthEastData(BlockData blockData) {
        super(blockData, BlockFace.EAST_SOUTH_EAST);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MultipleFacingEastSouthEastDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MultipleFacingEastSouthEastData(blockData);
    }
}
