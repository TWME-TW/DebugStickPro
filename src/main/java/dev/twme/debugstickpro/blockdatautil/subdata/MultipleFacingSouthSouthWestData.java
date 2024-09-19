package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class MultipleFacingSouthSouthWestData extends MultipleFacingData {

    public MultipleFacingSouthSouthWestData(BlockData blockData) {
        super(blockData, BlockFace.SOUTH_SOUTH_WEST);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MultipleFacingSouthSouthWestDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MultipleFacingSouthSouthWestData(blockData);
    }
}
