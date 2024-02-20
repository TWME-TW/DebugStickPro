package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Rail;

import java.util.List;

public class RailData implements SubBlockData {
    private final String NAME = "Rail";
    private BlockData blockData;
    private Rail.Shape shape;
    private boolean isUsing = false;

    public RailData(BlockData blockData) {
        this.blockData = blockData;
        this.shape = ((Rail) blockData).getShape();
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String dataName() {
        return LangFile.RailDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return shape.toString();
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
    public BlockData copyTo(BlockData blockData) {
        ((Rail) blockData).setShape(shape);
        return blockData;
    }
}
