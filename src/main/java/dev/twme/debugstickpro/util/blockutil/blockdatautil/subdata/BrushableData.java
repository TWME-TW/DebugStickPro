package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Brushable;

public class BrushableData implements SubBlockData {
    private BlockData blockData;
    private int dusted;
    private boolean isUsing = false;

    public BrushableData(BlockData blockData) {
        this.blockData = blockData;
        this.dusted = ((Brushable) blockData).getDusted();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public BlockData getData() {
        return blockData;
    }


    @Override
    public String getAsString() {
        return LangFile.Brushable.replace("%data%", getDataAsString());
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(dusted);
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
    public BlockData copyTo(BlockData blockData) {
        ((Brushable) blockData).setDusted(dusted);
        return blockData;
    }
}
