package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;

import java.util.ArrayList;
import java.util.List;

public class DirectionalData implements SubBlockData{
    private String NAME = "Direction";
    private org.bukkit.block.data.BlockData blockData;
    private BlockFace direction;
    public DirectionalData(org.bukkit.block.data.BlockData blockData){
        this.blockData = blockData;
        this.direction = ((Directional) blockData).getFacing();
    }
    @Override
    public String NAME() {
        return NAME;
    }

    @Override
    public org.bukkit.block.data.BlockData getData() {
        return blockData;
    }

    @Override
    public org.bukkit.block.data.BlockData getNextData() {
        nextDirectionProperty();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Direction: " + direction;
    }

    @Override
    public String getNextAsString() {
        nextDirectionProperty();
        return "Direction: " + direction;
    }

    @Override
    public String getDataAsString() {
        return direction.name();
    }

    @Override
    public String getNextDataAsString() {
        nextDirectionProperty();
        return direction.name();
    }

    private void nextDirectionProperty(){
        List<BlockFace> blockFaces = ((Directional) blockData).getFaces().stream().toList();
        int index = blockFaces.indexOf(direction);
        if (index >= blockFaces.size() - 1){
            ((Directional) blockData).setFacing(blockFaces.get(0));
        } else {
            ((Directional) blockData).setFacing(blockFaces.get(index + 1));
        }
        this.direction = ((Directional) blockData).getFacing();
    }
}
