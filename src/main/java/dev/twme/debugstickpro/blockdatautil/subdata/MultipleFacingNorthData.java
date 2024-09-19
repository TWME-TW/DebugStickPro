package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class MultipleFacingNorthData extends MultipleFacingData {

    public MultipleFacingNorthData(BlockData blockData) {
        super(blockData, BlockFace.NORTH);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MultipleFacingNorthDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MultipleFacingNorthData(blockData);
    }
}
