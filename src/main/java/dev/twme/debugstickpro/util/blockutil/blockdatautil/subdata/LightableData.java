package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Lightable;

public class LightableData implements SubBlockData {
    private String NAME = "Lightable";
    private BlockData blockData;
    private boolean lit;

    public LightableData(BlockData blockData) {
        this.blockData = blockData;
        this.lit = ((Lightable) blockData).isLit();
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
        nextLit();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Lit: " + lit;
    }

    @Override
    public String getNextAsString() {
        nextLit();
        return "Lit: " + lit;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(lit);
    }

    @Override
    public String getNextDataAsString() {
        nextLit();
        return String.valueOf(lit);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextLit() {
        Lightable lightable = ((Lightable) blockData);
        lightable.setLit(!lit);
        this.lit = lightable.isLit();
    }
}
