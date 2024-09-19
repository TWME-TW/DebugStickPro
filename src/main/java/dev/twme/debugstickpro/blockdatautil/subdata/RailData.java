package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Rail;

import java.util.List;

public class RailData extends SubBlockData {
    private Rail.Shape shape;

    public RailData(BlockData blockData) {
        this.blockData = blockData;
        this.shape = ((Rail) blockData).getShape();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.RailDataName;
    }

    @Override
    public String getDataAsString() {
        return shape.toString();
    }

    @Override
    public SubBlockData nextData() {
        Rail rail = ((Rail) blockData);
        List<Rail.Shape> shapes = rail.getShapes().stream().toList();
        int index = shapes.indexOf(shape);
        if (index >= shapes.size() - 1) {
            index = 0;
        } else {
            index++;
        }
        shape = shapes.get(index);
        rail.setShape(shape);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Rail rail = ((Rail) blockData);
        List<Rail.Shape> shapes = rail.getShapes().stream().toList();
        int index = shapes.indexOf(shape);
        if (index <= 0) {
            index = shapes.size() - 1;
        } else {
            index--;
        }
        shape = shapes.get(index);
        rail.setShape(shape);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Rail) blockData).setShape(shape);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new RailData(blockData);
    }
}
