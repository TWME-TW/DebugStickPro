package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.Axis;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Orientable;

import java.util.List;

public class OrientableData implements SubBlockData{
    private final String NAME = "Orientable";
    private BlockData blockData;
    private Axis axis;

    public OrientableData(BlockData blockData) {
        this.blockData = blockData;
        this.axis = ((org.bukkit.block.data.Orientable) blockData).getAxis();
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
        nextAxis();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Axis: " + axis;
    }

    @Override
    public String getNextAsString() {
        nextAxis();
        return "Axis: " + axis;
    }

    @Override
    public String getDataAsString() {
        return axis.name();
    }

    @Override
    public String getNextDataAsString() {
        nextAxis();
        return axis.name();
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextAxis() {
        Orientable orientable = (Orientable) blockData;
        List<Axis> axisList = orientable.getAxes().stream().toList();
        if (axisList.size() == 1) {
            orientable.setAxis(axisList.get(0));
            this.axis = axisList.get(0);
        } else {
            int index = axisList.indexOf(axis);
            if (index == axisList.size() - 1) {
                orientable.setAxis(axisList.get(0));
                this.axis = axisList.get(0);
            } else {
                orientable.setAxis(axisList.get(index + 1));
                this.axis = axisList.get(index + 1);
            }
        }
        orientable.setAxis(axis);
    }
}
