package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class MultipleFacingWestNorthWestData extends MultipleFacingData {

    public MultipleFacingWestNorthWestData(BlockData blockData) {
        super(blockData, BlockFace.WEST_NORTH_WEST);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MultipleFacingWestNorthWestDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MultipleFacingWestNorthWestData(blockData);
    }
}
