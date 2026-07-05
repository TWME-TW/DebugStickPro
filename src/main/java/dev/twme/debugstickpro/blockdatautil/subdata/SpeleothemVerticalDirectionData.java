package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Speleothem;

import java.util.List;

public class SpeleothemVerticalDirectionData extends SubBlockData {
    private BlockFace verticalDirection;

    public SpeleothemVerticalDirectionData(BlockData blockData) {
        this.blockData = blockData;
        this.verticalDirection = ((Speleothem) blockData).getVerticalDirection();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.SpeleothemVerticalDirectionDataName;
    }

    @Override
    public String getDataAsString() {
        return verticalDirection.name();
    }

    @Override
    public SubBlockData nextData() {
        Speleothem speleothem = (Speleothem) blockData;
        List<BlockFace> blockFaces = speleothem.getVerticalDirections().stream().toList();
        verticalDirection = blockFaces.get((blockFaces.indexOf(verticalDirection) + 1) % blockFaces.size());
        speleothem.setVerticalDirection(verticalDirection);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Speleothem speleothem = (Speleothem) blockData;
        List<BlockFace> blockFaces = speleothem.getVerticalDirections().stream().toList();
        verticalDirection = blockFaces.get((blockFaces.indexOf(verticalDirection) - 1 + blockFaces.size()) % blockFaces.size());
        speleothem.setVerticalDirection(verticalDirection);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Speleothem) blockData).setVerticalDirection(verticalDirection);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new SpeleothemVerticalDirectionData(blockData);
    }
}
