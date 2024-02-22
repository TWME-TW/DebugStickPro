package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Comparator;

public class ComparatorData implements SubBlockData {

    private BlockData blockData;
    private Comparator.Mode mode;
    private boolean isUsing = false;

    public ComparatorData(BlockData blockData) {
        this.blockData = blockData;
        this.mode = ((Comparator) blockData).getMode();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.ComparatorDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return mode.name();
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
        Comparator comparator = ((Comparator) blockData);
        if (comparator.getMode() == Comparator.Mode.COMPARE) {
            comparator.setMode(Comparator.Mode.SUBTRACT);
        } else {
            comparator.setMode(Comparator.Mode.COMPARE);
        }
        this.mode = comparator.getMode();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Comparator) blockData).setMode(mode);
        return blockData;
    }
}