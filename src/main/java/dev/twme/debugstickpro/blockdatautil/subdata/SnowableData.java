package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Snowable;

public class SnowableData implements SubBlockData {
    private final BlockData blockData;
    private boolean snowy;
    private boolean isUsing = false;

    public SnowableData(BlockData blockData) {
        this.blockData = blockData;
        this.snowy = ((Snowable) blockData).isSnowy();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.SnowableDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(snowy);
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
    public SubBlockData getDataFac(BlockData blockData) {
        return new SnowableData(blockData);
    }
}
