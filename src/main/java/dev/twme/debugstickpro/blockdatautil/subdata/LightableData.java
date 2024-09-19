package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Lightable;

public class LightableData extends SubBlockData {
    private boolean lit;

    public LightableData(BlockData blockData) {
        this.blockData = blockData;
        this.lit = ((Lightable) blockData).isLit();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.LightableDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(lit);
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

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new LightableData(blockData);
    }
}
