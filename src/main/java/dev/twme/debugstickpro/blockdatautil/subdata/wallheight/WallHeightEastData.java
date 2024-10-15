package dev.twme.debugstickpro.blockdatautil.subdata.wallheight;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class WallHeightEastData extends WallHeightData {

    public WallHeightEastData(BlockData blockData) {
        super(blockData, BlockFace.EAST);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.WallHeightEastDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new WallHeightEastData(blockData);
    }
}
