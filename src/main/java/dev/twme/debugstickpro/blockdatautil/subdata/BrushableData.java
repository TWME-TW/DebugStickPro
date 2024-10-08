package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Brushable;

public class BrushableData extends SubBlockData {
    private int dusted;

    public BrushableData(BlockData blockData) {
        this.blockData = blockData;
        this.dusted = ((Brushable) blockData).getDusted();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.BrushableDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(dusted);
    }

    public SubBlockData nextData() {
        Brushable brushable = ((Brushable) blockData);
        if (brushable.getDusted() >= brushable.getMaximumDusted()) {
            brushable.setDusted(0);
        } else {
            brushable.setDusted(brushable.getDusted() + 1);
        }
        this.dusted = brushable.getDusted();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Brushable brushable = ((Brushable) blockData);
        if (brushable.getDusted() <= 0) {
            brushable.setDusted(brushable.getMaximumDusted());
        } else {
            brushable.setDusted(brushable.getDusted() - 1);
        }
        this.dusted = brushable.getDusted();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Brushable) blockData).setDusted(dusted);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new BrushableData(blockData);
    }
}
