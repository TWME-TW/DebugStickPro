package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Comparator;

public class ComparatorData implements SubBlockData{
    private String NAME = "Comparator Mode";
    private BlockData blockData;
    private Comparator.Mode mode;
    public ComparatorData(BlockData blockData){
        this.blockData = blockData;
        this.mode = ((Comparator) blockData).getMode();
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
        nextModeProperty();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Comparator Mode: " + mode;
    }

    @Override
    public String getNextAsString() {
        nextModeProperty();
        return "Comparator Mode: " + mode;
    }

    @Override
    public String getDataAsString() {
        return mode.name();
    }

    @Override
    public String getNextDataAsString() {
        nextModeProperty();
        return mode.name();
    }

    private void nextModeProperty(){
        Comparator comparator = ((Comparator) blockData);
        if (comparator.getMode() == Comparator.Mode.COMPARE){
            comparator.setMode(Comparator.Mode.SUBTRACT);
        } else {
            comparator.setMode(Comparator.Mode.COMPARE);
        }
        this.mode = comparator.getMode();
    }
}
