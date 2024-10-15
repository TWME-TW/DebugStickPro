package dev.twme.debugstickpro.blockdatautil.subdata.wallheight;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class WallHeightNorthData extends WallHeightData {

    public WallHeightNorthData(BlockData blockData) {
        super(blockData, BlockFace.NORTH);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.WallHeightNorthDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new WallHeightNorthData(blockData);
    }
}
