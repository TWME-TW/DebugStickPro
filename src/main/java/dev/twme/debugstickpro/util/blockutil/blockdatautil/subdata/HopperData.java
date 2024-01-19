package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Hopper;

public class HopperData implements SubBlockData{
    private final String NAME = "Hopper Enabled";
    private BlockData blockData;
    private boolean enabled;
    public HopperData(BlockData blockData){
        this.blockData = blockData;
        this.enabled = ((Hopper) blockData).isEnabled();
    }

    @Override
    public String NAME() {
        return NAME;
    }

    @Override
    public BlockData getData() {
        return blockData;
    }

    @Override
    public BlockData getNextData() {
        nextEnabledProperty();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Enabled: " + enabled;
    }

    @Override
    public String getNextAsString() {
        nextEnabledProperty();
        return "Enabled: " + enabled;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(enabled);
    }

    @Override
    public String getNextDataAsString() {
        nextEnabledProperty();
        return String.valueOf(enabled);
    }
    private void nextEnabledProperty(){
        Hopper hopper = ((Hopper) blockData);
        hopper.setEnabled(!hopper.isEnabled());
        this.enabled = hopper.isEnabled();
    }
}
