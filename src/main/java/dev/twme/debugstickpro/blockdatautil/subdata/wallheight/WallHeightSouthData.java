package dev.twme.debugstickpro.blockdatautil.subdata.wallheight;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class WallHeightSouthData extends WallHeightData {

    public WallHeightSouthData(BlockData blockData) {
        super(blockData, BlockFace.SOUTH);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.WallHeightSouthDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new WallHeightSouthData(blockData);
    }
}
