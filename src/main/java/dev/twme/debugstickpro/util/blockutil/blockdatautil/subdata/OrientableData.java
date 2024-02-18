package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.Axis;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Orientable;

import java.util.List;

public class OrientableData implements SubBlockData{
    private final String NAME = "Orientable";
    private BlockData blockData;
    private Axis axis;
    private boolean isUsing = false;

    public OrientableData(BlockData blockData) {
        this.blockData = blockData;
        this.axis = ((org.bukkit.block.data.Orientable) blockData).getAxis();
    }
    @Override
    public String name() {
        return NAME;
    }

    @Override
    public BlockData getData() {
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Axis: " + axis;
    }

    @Override
    public String getDataAsString() {
        return axis.name();
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
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Orientable)blockData).setAxis(axis);
        return blockData;
    }
}
