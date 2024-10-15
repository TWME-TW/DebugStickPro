package dev.twme.debugstickpro.blockdatautil.subdata.multiplefacing;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.MultipleFacing;

public class MultipleFacingSouthWestData extends MultipleFacingData {

    public MultipleFacingSouthWestData(BlockData blockData) {
        super(blockData, BlockFace.SOUTH_WEST);
        this.blockData = blockData;
        this.has = ((MultipleFacing) blockData).hasFace(face);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MultipleFacingSouthWestDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MultipleFacingSouthWestData(blockData);
    }
}
