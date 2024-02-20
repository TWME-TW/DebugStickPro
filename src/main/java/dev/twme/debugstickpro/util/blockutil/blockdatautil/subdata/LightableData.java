package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Lightable;

public class LightableData implements SubBlockData {
    private String NAME = "Lightable";
    private BlockData blockData;
    private boolean lit;
    private boolean isUsing = false;

    public LightableData(BlockData blockData) {
        this.blockData = blockData;
        this.lit = ((Lightable) blockData).isLit();
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }



    @Override
    public String getAsString() {
        return "Lit: " + lit;
    }



    @Override
    public String getDataAsString() {
        return String.valueOf(lit);
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

    @Override
    public SubBlockData nextData() {
        Lightable lightable = ((Lightable) blockData);
        lightable.setLit(!lit);
        this.lit = lightable.isLit();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Lightable)blockData).setLit(lit);
        return blockData;
    }
}
