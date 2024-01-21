package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Stairs;

public class StairsData implements SubBlockData{
    private String NAME = "Stairs";
    private BlockData blockData;
    private Stairs.Shape shape;
    public StairsData(BlockData blockData){
        this.blockData = blockData;
        this.shape = ((Stairs) blockData).getShape();
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
        nextData();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Shape: " + shape;
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "Shape: " + shape;
    }

    @Override
    public String getDataAsString() {
        return shape.name();
    }

    @Override
    public String getNextDataAsString() {
        return shape.name();
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData(){
        Stairs stairs = ((Stairs) blockData);
        if (shape == Stairs.Shape.INNER_LEFT){
            shape = Stairs.Shape.INNER_RIGHT;
        } else if (shape == Stairs.Shape.INNER_RIGHT){
            shape = Stairs.Shape.STRAIGHT;
        } else if (shape == Stairs.Shape.STRAIGHT){
            shape = Stairs.Shape.OUTER_LEFT;
        } else if (shape == Stairs.Shape.OUTER_LEFT){
            shape = Stairs.Shape.OUTER_RIGHT;
        } else if (shape == Stairs.Shape.OUTER_RIGHT){
            shape = Stairs.Shape.INNER_LEFT;
        }
        stairs.setShape(shape);
    }
}
