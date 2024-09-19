package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class MultipleFacingNorthEastData extends MultipleFacingData {

    public MultipleFacingNorthEastData(BlockData blockData) {
        super(blockData, BlockFace.NORTH_EAST);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MultipleFacingNorthEastDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MultipleFacingNorthEastData(blockData);
    }
}
