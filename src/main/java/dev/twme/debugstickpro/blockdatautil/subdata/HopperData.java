package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Hopper;

public class HopperData extends SubBlockData {
    private boolean enabled;

    public HopperData(BlockData blockData) {
        this.blockData = blockData;
        this.enabled = ((Hopper) blockData).isEnabled();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.HopperDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(enabled);
    }

    public SubBlockData nextData() {
        Hopper hopper = ((Hopper) blockData);
        hopper.setEnabled(!hopper.isEnabled());
        this.enabled = hopper.isEnabled();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Hopper) blockData).setEnabled(enabled);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new HopperData(blockData);
    }
}
