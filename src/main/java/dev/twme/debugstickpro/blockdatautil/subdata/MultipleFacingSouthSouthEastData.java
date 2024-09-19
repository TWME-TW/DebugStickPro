package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class MultipleFacingSouthSouthEastData extends MultipleFacingData {

    public MultipleFacingSouthSouthEastData(BlockData blockData) {
        super(blockData, BlockFace.SOUTH_SOUTH_EAST);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MultipleFacingSouthSouthEastDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MultipleFacingSouthSouthEastData(blockData);
    }
}
