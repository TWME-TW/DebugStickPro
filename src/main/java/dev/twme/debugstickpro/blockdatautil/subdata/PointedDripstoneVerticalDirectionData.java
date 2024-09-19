package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.PointedDripstone;

import java.util.List;

public class PointedDripstoneVerticalDirectionData extends SubBlockData {
    private BlockFace verticalDirection;

    public PointedDripstoneVerticalDirectionData(BlockData blockData) {
        this.blockData = blockData;
        this.verticalDirection = ((PointedDripstone) blockData).getVerticalDirection();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.PointedDripstoneVerticalDirectionDataName;
    }

    @Override
    public String getDataAsString() {
        return verticalDirection.name();
    }

    public SubBlockData nextData() {
        PointedDripstone pointedDripstone = ((PointedDripstone) blockData);
        List<BlockFace> blockFaces = pointedDripstone.getVerticalDirections().stream().toList();
        verticalDirection = blockFaces.get((blockFaces.indexOf(verticalDirection) + 1) % blockFaces.size());
        pointedDripstone.setVerticalDirection(verticalDirection);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        PointedDripstone pointedDripstone = ((PointedDripstone) blockData);
        List<BlockFace> blockFaces = pointedDripstone.getVerticalDirections().stream().toList();
        verticalDirection = blockFaces.get((blockFaces.indexOf(verticalDirection) - 1 + blockFaces.size()) % blockFaces.size());
        pointedDripstone.setVerticalDirection(verticalDirection);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((PointedDripstone) blockData).setVerticalDirection(verticalDirection);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new PointedDripstoneVerticalDirectionData(blockData);
    }
}
