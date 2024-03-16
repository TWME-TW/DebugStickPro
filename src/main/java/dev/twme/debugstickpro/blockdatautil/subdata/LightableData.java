package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Lightable;

public class LightableData implements SubBlockData {
    private BlockData blockData;
    private boolean lit;
    private boolean isUsing = false;

    public LightableData(BlockData blockData) {
        this.blockData = blockData;
        this.lit = ((Lightable) blockData).isLit();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.LightableDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
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
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Lightable) blockData).setLit(lit);
        return blockData;
    }
}
