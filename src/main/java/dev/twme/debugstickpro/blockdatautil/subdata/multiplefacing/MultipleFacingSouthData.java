package dev.twme.debugstickpro.blockdatautil.subdata.multiplefacing;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class MultipleFacingSouthData extends MultipleFacingData {

    public MultipleFacingSouthData(BlockData blockData) {
        super(blockData, BlockFace.SOUTH);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MultipleFacingSouthDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MultipleFacingSouthData(blockData);
    }
}
