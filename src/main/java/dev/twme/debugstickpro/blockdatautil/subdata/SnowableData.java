package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Snowable;

public class SnowableData extends SubBlockData {
    private boolean snowy;

    public SnowableData(BlockData blockData) {
        this.blockData = blockData;
        this.snowy = ((Snowable) blockData).isSnowy();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.SnowableDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(snowy);
    }

    public SubBlockData nextData() {
        Snowable snowable = ((Snowable) blockData);
        snowy = !snowy;
        snowable.setSnowy(snowy);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Snowable) blockData).setSnowy(snowy);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new SnowableData(blockData);
    }
}
