package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;

import java.util.List;

public class DirectionalData implements SubBlockData {
    private org.bukkit.block.data.BlockData blockData;
    private BlockFace direction;
    private boolean isUsing = false;

    public DirectionalData(org.bukkit.block.data.BlockData blockData) {
        this.blockData = blockData;
        this.direction = ((Directional) blockData).getFacing();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.DirectionalDataName;
    }

    @Override
    public org.bukkit.block.data.BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return direction.name();
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
        List<BlockFace> blockFaces = ((Directional) blockData).getFaces().stream().toList();
        int index = blockFaces.indexOf(direction);
        if (index >= blockFaces.size() - 1) {
            ((Directional) blockData).setFacing(blockFaces.get(0));
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
            ((Directional) blockData).setFacing(blockFaces.get(blockFaces.size() - 1));
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
}
