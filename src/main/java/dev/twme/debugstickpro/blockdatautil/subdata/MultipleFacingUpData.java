package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class MultipleFacingUpData extends MultipleFacingData{
    public MultipleFacingUpData(BlockData blockData) {
        super(blockData, BlockFace.UP);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MultipleFacingUpDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MultipleFacingUpData(blockData);
    }

}
