package dev.twme.debugstickpro.blockdatautil.subdata.multiplefacing;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class MultipleFacingNorthNorthEastData extends MultipleFacingData {

    public MultipleFacingNorthNorthEastData(BlockData blockData) {
        super(blockData, BlockFace.NORTH_NORTH_EAST);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MultipleFacingNorthNorthEastDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MultipleFacingNorthNorthEastData(blockData);
    }
}
