package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class MultipleFacingSelfData extends MultipleFacingData {

    public MultipleFacingSelfData(BlockData blockData) {
        super(blockData, BlockFace.SELF);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MultipleFacingSelfDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MultipleFacingSelfData(blockData);
    }
}
