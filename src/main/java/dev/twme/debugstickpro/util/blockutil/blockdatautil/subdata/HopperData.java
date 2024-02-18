package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Hopper;

public class HopperData implements SubBlockData{
    private final String NAME = "Hopper Enabled";
    private BlockData blockData;
    private boolean enabled;
    private boolean isUsing = false;
    public HopperData(BlockData blockData){
        this.blockData = blockData;
        this.enabled = ((Hopper) blockData).isEnabled();
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public BlockData getData() {
        return blockData;
    }



    @Override
    public String getAsString() {
        return "Enabled: " + enabled;
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

    public SubBlockData nextData(){
        Hopper hopper = ((Hopper) blockData);
        hopper.setEnabled(!hopper.isEnabled());
        this.enabled = hopper.isEnabled();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Hopper)blockData).setEnabled(enabled);
        return blockData;
    }
}
