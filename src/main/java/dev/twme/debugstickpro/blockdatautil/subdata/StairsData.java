package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Stairs;

public class StairsData extends SubBlockData {
    private Stairs.Shape shape;

    public StairsData(BlockData blockData) {
        this.blockData = blockData;
        this.shape = ((Stairs) blockData).getShape();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.StairsDataName;
    }

    @Override
    public String getDataAsString() {
        return shape.name();
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

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new StairsData(blockData);
    }
}
