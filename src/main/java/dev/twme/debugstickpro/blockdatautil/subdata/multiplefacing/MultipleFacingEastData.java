package dev.twme.debugstickpro.blockdatautil.subdata.multiplefacing;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.MultipleFacing;

public class MultipleFacingEastData extends MultipleFacingData {

    public MultipleFacingEastData(BlockData blockData) {
        super(blockData, BlockFace.EAST);
        this.blockData = blockData;
        this.has = ((MultipleFacing) blockData).hasFace(face);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.MultipleFacingEastDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new MultipleFacingEastData(blockData);
    }
}
