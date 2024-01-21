package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Rotatable;

import java.util.List;

public class RotatableData implements SubBlockData{
    private String NAME = "Rotatable";
    private BlockData blockData;
    private BlockFace blockFace;
    public RotatableData(BlockData blockData) {
        this.blockData = blockData;
        this.blockFace = ((Rotatable) blockData).getRotation();
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
        return "Rotation: " + blockFace;
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "Rotation: " + blockFace;
    }

    @Override
    public String getDataAsString() {
        return blockFace.name();
    }

    @Override
    public String getNextDataAsString() {
        nextData();
        return blockFace.name();
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData() {
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
    }
}
