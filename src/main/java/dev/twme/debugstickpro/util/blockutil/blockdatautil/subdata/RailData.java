package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Rail;

import java.util.List;

public class RailData implements SubBlockData{
    private final String NAME = "Rail";
    private BlockData blockData;
    private Rail.Shape shape;
    public RailData(BlockData blockData) {
        this.blockData = blockData;
        this.shape = ((Rail) blockData).getShape();
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
    public BlockData getNextData() {
        return null;
    }

    @Override
    public String getAsString() {
        return null;
    }

    @Override
    public String getNextAsString() {
        return null;
    }

    @Override
    public String getDataAsString() {
        return null;
    }

    @Override
    public String getNextDataAsString() {
        return null;
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData(){
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
    }
}
