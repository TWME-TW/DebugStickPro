package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Rotatable;

import java.util.LinkedList;

public class RotatableData extends SubBlockData {
    private BlockFace blockFace;

    public RotatableData(BlockData blockData) {
        this.blockData = blockData;
        this.blockFace = ((Rotatable) blockData).getRotation();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.RotatableDataName;
    }

    @Override
    public String getDataAsString() {
        return blockFace.name();
    }

    @Override
    public SubBlockData nextData() {
        Rotatable rotatable = ((Rotatable) blockData);
        LinkedList<BlockFace> blockFaces = getSortedBlockFaces();

        int index = blockFaces.indexOf(blockFace);
        if (index >= blockFaces.size() - 1) {
            index = 0;
        } else {
            index++;
        }
        rotatable.setRotation(blockFaces.get(index));
        this.blockFace = blockFaces.get(index);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Rotatable rotatable = ((Rotatable) blockData);
        LinkedList<BlockFace> blockFaces = getSortedBlockFaces();

        int index = blockFaces.indexOf(blockFace);
        if (index <= 0) {
            index = blockFaces.size() - 1;
        } else {
            index--;
        }
        rotatable.setRotation(blockFaces.get(index));
        this.blockFace = blockFaces.get(index);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Rotatable) blockData).setRotation(blockFace);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new RotatableData(blockData);
    }

    public static LinkedList<BlockFace> getSortedBlockFaces() {
        LinkedList<BlockFace> blockFaces = new LinkedList<>();

        blockFaces.add(BlockFace.NORTH);
        blockFaces.add(BlockFace.NORTH_NORTH_EAST);
        blockFaces.add(BlockFace.NORTH_EAST);
        blockFaces.add(BlockFace.EAST_NORTH_EAST);
        blockFaces.add(BlockFace.EAST);
        blockFaces.add(BlockFace.EAST_SOUTH_EAST);
        blockFaces.add(BlockFace.SOUTH_EAST);
        blockFaces.add(BlockFace.SOUTH_SOUTH_EAST);
        blockFaces.add(BlockFace.SOUTH);
        blockFaces.add(BlockFace.SOUTH_SOUTH_WEST);
        blockFaces.add(BlockFace.SOUTH_WEST);
        blockFaces.add(BlockFace.WEST_SOUTH_WEST);
        blockFaces.add(BlockFace.WEST);
        blockFaces.add(BlockFace.WEST_NORTH_WEST);
        blockFaces.add(BlockFace.NORTH_WEST);
        blockFaces.add(BlockFace.NORTH_NORTH_WEST);

        return blockFaces;
    }
}
