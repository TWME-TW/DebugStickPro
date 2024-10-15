package dev.twme.debugstickpro.blockdatautil.subdata.redstonewire;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class RedstoneWireEastData extends RedstoneWireData {

    public RedstoneWireEastData(BlockData blockData) {
        super(blockData, BlockFace.EAST);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.RedstoneWireEastDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new RedstoneWireEastData(blockData);
    }
}
