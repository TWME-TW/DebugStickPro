package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class WallHeightWestData extends WallHeightData {

    public WallHeightWestData(BlockData blockData) {
        super(blockData, BlockFace.WEST);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.WallHeightWestDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new WallHeightWestData(blockData);
    }
}
