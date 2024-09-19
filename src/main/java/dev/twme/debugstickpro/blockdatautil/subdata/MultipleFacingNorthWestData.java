package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class MultipleFacingNorthWestData extends MultipleFacingData {

    public MultipleFacingNorthWestData(BlockData blockData) {
        super(blockData, BlockFace.NORTH_WEST);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MultipleFacingNorthWestDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MultipleFacingNorthWestData(blockData);
    }
}
