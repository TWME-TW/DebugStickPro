package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class MultipleFacingEastNorthEastData extends MultipleFacingData {

    public MultipleFacingEastNorthEastData(BlockData blockData) {
        super(blockData, BlockFace.EAST_NORTH_EAST);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MultipleFacingEastNorthEastDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MultipleFacingEastNorthEastData(blockData);
    }
}
