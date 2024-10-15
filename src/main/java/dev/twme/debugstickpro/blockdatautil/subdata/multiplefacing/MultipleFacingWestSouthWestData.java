package dev.twme.debugstickpro.blockdatautil.subdata.multiplefacing;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class MultipleFacingWestSouthWestData extends MultipleFacingData {

    public MultipleFacingWestSouthWestData(BlockData blockData) {
        super(blockData, BlockFace.WEST_SOUTH_WEST);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MultipleFacingWestSouthWestDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MultipleFacingWestSouthWestData(blockData);
    }
}
