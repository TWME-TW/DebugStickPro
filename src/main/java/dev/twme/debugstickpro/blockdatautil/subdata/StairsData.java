package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Stairs;

public class StairsData implements SubBlockData {
    private BlockData blockData;
    private Stairs.Shape shape;
    private boolean isUsing = false;

    public StairsData(BlockData blockData) {
        this.blockData = blockData;
        this.shape = ((Stairs) blockData).getShape();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.StairsDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return shape.name();
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

    public SubBlockData nextData() {
        Stairs stairs = ((Stairs) blockData);
        if (shape == Stairs.Shape.INNER_LEFT) {
            shape = Stairs.Shape.INNER_RIGHT;
        } else if (shape == Stairs.Shape.INNER_RIGHT) {
            shape = Stairs.Shape.STRAIGHT;
        } else if (shape == Stairs.Shape.STRAIGHT) {
            shape = Stairs.Shape.OUTER_LEFT;
        } else if (shape == Stairs.Shape.OUTER_LEFT) {
            shape = Stairs.Shape.OUTER_RIGHT;
        } else if (shape == Stairs.Shape.OUTER_RIGHT) {
            shape = Stairs.Shape.INNER_LEFT;
        }
        stairs.setShape(shape);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Stairs stairs = ((Stairs) blockData);
        if (shape == Stairs.Shape.STRAIGHT) {
            shape = Stairs.Shape.OUTER_RIGHT;
        } else if (shape == Stairs.Shape.OUTER_RIGHT) {
            shape = Stairs.Shape.OUTER_LEFT;
        } else if (shape == Stairs.Shape.OUTER_LEFT) {
            shape = Stairs.Shape.INNER_RIGHT;
        } else if (shape == Stairs.Shape.INNER_RIGHT) {
            shape = Stairs.Shape.INNER_LEFT;
        } else if (shape == Stairs.Shape.INNER_LEFT) {
            shape = Stairs.Shape.STRAIGHT;
        }

        stairs.setShape(shape);
        return this;
    }


    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Stairs) blockData).setShape(shape);
        return blockData;
    }
}
