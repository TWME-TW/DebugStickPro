package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class MultipleFacingNorthNorthWestData extends MultipleFacingData {

    public MultipleFacingNorthNorthWestData(BlockData blockData) {
        super(blockData, BlockFace.NORTH_NORTH_WEST);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MultipleFacingNorthNorthWestDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MultipleFacingNorthNorthWestData(blockData);
    }
}
