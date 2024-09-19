package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;

import java.util.List;

public class DirectionalData extends SubBlockData {
    private BlockFace direction;

    public DirectionalData(BlockData blockData) {
        this.blockData = blockData;
        this.direction = ((Directional) blockData).getFacing();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.DirectionalDataName;
    }

    @Override
    public String getDataAsString() {
        return direction.name();
    }

    @Override
    public SubBlockData nextData() {
        List<BlockFace> blockFaces = ((Directional) blockData).getFaces().stream().toList();
        int index = blockFaces.indexOf(direction);
        if (index >= blockFaces.size() - 1) {
            ((Directional) blockData).setFacing(blockFaces.getFirst());
        } else {
            ((Directional) blockData).setFacing(blockFaces.get(index + 1));
        }
        this.direction = ((Directional) blockData).getFacing();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        List<BlockFace> blockFaces = ((Directional) blockData).getFaces().stream().toList();
        int index = blockFaces.indexOf(direction);
        if (index <= 0) {
            ((Directional) blockData).setFacing(blockFaces.getLast());
        } else {
            ((Directional) blockData).setFacing(blockFaces.get(index - 1));
        }
        this.direction = ((Directional) blockData).getFacing();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Directional) blockData).setFacing(direction);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new DirectionalData(blockData);
    }
}
