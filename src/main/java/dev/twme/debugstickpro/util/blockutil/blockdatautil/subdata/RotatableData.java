package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Rotatable;

import java.util.List;

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
        return LangFile.RotatableDataName;
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
        List<BlockFace> blockFaces = List.of(BlockFace.values());
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
    public BlockData copyTo(BlockData blockData) {
        return null;
    }
}
