package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Rotatable;

import java.util.ArrayList;
import java.util.Arrays;

public class RotatableData implements SubBlockData {
    private BlockData blockData;
    private BlockFace blockFace;
    private boolean isUsing = false;

    public RotatableData(BlockData blockData) {
        this.blockData = blockData;
        this.blockFace = ((Rotatable) blockData).getRotation();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.RotatableDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }

    @Override
    public String getDataAsString() {
        return blockFace.name();
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
        Rotatable rotatable = ((Rotatable) blockData);
        ArrayList<BlockFace> blockFaces = new ArrayList<>(Arrays.asList(BlockFace.values()));

        blockFaces.remove(BlockFace.SELF); // Remove SELF (the default value of BlockFace
        blockFaces.remove(BlockFace.UP); // Remove UP (the default value of BlockFace
        blockFaces.remove(BlockFace.DOWN); // Remove DOWN (the default value of BlockFace

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
        ArrayList<BlockFace> blockFaces = new ArrayList<>(Arrays.asList(BlockFace.values()));

        blockFaces.remove(BlockFace.SELF); // Remove SELF (the default value of BlockFace
        blockFaces.remove(BlockFace.UP); // Remove UP (the default value of BlockFace
        blockFaces.remove(BlockFace.DOWN); // Remove DOWN (the default value of BlockFace

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
}
