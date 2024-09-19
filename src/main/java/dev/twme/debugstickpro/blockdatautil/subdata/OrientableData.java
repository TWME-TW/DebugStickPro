package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.Axis;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Orientable;

import java.util.List;

public class OrientableData extends SubBlockData {
    private Axis axis;

    public OrientableData(BlockData blockData) {
        this.blockData = blockData;
        this.axis = ((org.bukkit.block.data.Orientable) blockData).getAxis();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.OrientableDataName;
    }

    @Override
    public String getDataAsString() {
        return axis.name();
    }

    @Override
    public SubBlockData nextData() {
        Orientable orientable = (Orientable) blockData;
        List<Axis> axisList = orientable.getAxes().stream().toList();
        if (axisList.size() == 1) {
            orientable.setAxis(axisList.getFirst());
            this.axis = axisList.getFirst();
        } else {
            int index = axisList.indexOf(axis);
            if (index == axisList.size() - 1) {
                orientable.setAxis(axisList.getFirst());
                this.axis = axisList.getFirst();
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
            orientable.setAxis(axisList.getFirst());
            this.axis = axisList.getFirst();
        } else {
            int index = axisList.indexOf(axis);
            if (index == 0) {
                orientable.setAxis(axisList.getLast());
                this.axis = axisList.getLast();
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
