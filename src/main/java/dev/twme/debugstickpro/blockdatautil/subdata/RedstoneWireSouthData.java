package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class RedstoneWireSouthData extends RedstoneWireData {

    public RedstoneWireSouthData(BlockData blockData) {
        super(blockData, BlockFace.SOUTH);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.RedstoneWireSouthDataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new RedstoneWireSouthData(blockData);
    }
}
