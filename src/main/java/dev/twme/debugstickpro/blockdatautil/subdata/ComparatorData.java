package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Comparator;

public class ComparatorData extends SubBlockData {
    private Comparator.Mode mode;

    public ComparatorData(BlockData blockData) {
        this.blockData = blockData;
        this.mode = ((Comparator) blockData).getMode();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.ComparatorDataName;
    }

    @Override
    public String getDataAsString() {
        return mode.name();
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
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Comparator) blockData).setMode(mode);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new ComparatorData(blockData);
    }
}
