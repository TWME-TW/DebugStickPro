package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.MultipleFacing;

public class MultipleFacingSouthEastData extends MultipleFacingData {

    public MultipleFacingSouthEastData(BlockData blockData) {
        super(blockData, BlockFace.SOUTH_EAST);
        this.blockData = blockData;
        this.has = ((MultipleFacing) blockData).hasFace(face);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MultipleFacingSouthEastDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MultipleFacingSouthEastData(blockData);
    }
}
