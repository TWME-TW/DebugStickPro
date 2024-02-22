package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Hopper;

public class HopperData implements SubBlockData {
    private BlockData blockData;
    private boolean enabled;
    private boolean isUsing = false;

    public HopperData(BlockData blockData) {
        this.blockData = blockData;
        this.enabled = ((Hopper) blockData).isEnabled();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.HopperDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(enabled);
    }


    @Override
    public SubBlockData setIsUsing(boolean isUsing) {
        this.isUsing = isUsing;
        return this;
    }

    @Override
    public boolean isUsing() {
        return isUsing;
    }

    public SubBlockData nextData() {
        Hopper hopper = ((Hopper) blockData);
        hopper.setEnabled(!hopper.isEnabled());
        this.enabled = hopper.isEnabled();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Hopper) blockData).setEnabled(enabled);
        return blockData;
    }
}