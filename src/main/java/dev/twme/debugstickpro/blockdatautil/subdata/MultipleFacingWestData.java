package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class MultipleFacingWestData extends MultipleFacingData {

    public MultipleFacingWestData(BlockData blockData) {
        super(blockData, BlockFace.WEST);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MultipleFacingWestDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MultipleFacingWestData(blockData);
    }
}
