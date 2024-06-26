package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.Axis;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Orientable;

import java.util.List;

public class OrientableData implements SubBlockData {
    private final BlockData blockData;
    private Axis axis;
    private boolean isUsing = false;

    public OrientableData(BlockData blockData) {
        this.blockData = blockData;
        this.axis = ((org.bukkit.block.data.Orientable) blockData).getAxis();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.OrientableDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
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
    public SubBlockData previousData() {
        Orientable orientable = (Orientable) blockData;
        List<Axis> axisList = orientable.getAxes().stream().toList();
        if (axisList.size() == 1) {
            orientable.setAxis(axisList.get(0));
            this.axis = axisList.get(0);
        } else {
            int index = axisList.indexOf(axis);
            if (index == 0) {
                orientable.setAxis(axisList.get(axisList.size() - 1));
                this.axis = axisList.get(axisList.size() - 1);
            } else {
                orientable.setAxis(axisList.get(index - 1));
                this.axis = axisList.get(index - 1);
            }
        }
        orientable.setAxis(axis);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Orientable) blockData).setAxis(axis);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new OrientableData(blockData);
    }
}
