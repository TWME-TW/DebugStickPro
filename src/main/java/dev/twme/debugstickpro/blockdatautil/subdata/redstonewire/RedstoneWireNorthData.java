package dev.twme.debugstickpro.blockdatautil.subdata.redstonewire;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class RedstoneWireNorthData extends RedstoneWireData {

    public RedstoneWireNorthData(BlockData blockData) {
        super(blockData, BlockFace.NORTH);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.RedstoneWireNorthDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new RedstoneWireNorthData(blockData);
    }
}
