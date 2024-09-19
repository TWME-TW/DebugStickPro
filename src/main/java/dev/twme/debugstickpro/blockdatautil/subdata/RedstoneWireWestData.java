package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class RedstoneWireWestData extends RedstoneWireData {
    final private BlockFace face = BlockFace.WEST;

    public RedstoneWireWestData(BlockData blockData) {
        super(blockData, BlockFace.WEST);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.RedstoneWireWestDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new RedstoneWireWestData(blockData);
    }
}
