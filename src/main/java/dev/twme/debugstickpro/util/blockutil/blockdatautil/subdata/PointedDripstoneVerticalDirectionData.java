package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.PointedDripstone;

import java.util.List;

public class PointedDripstoneVerticalDirectionData implements SubBlockData{
    private final String NAME = "PointedDripstoneVerticalDirection";
    private BlockData blockData;
    private BlockFace verticalDirection;
    public PointedDripstoneVerticalDirectionData(BlockData blockData) {
        this.blockData = blockData;
        this.verticalDirection = ((PointedDripstone) blockData).getVerticalDirection();
    }
    @Override
    public String NAME() {
        return NAME;
    }

    @Override
    public BlockData getData() {
        return blockData;
    }

    @Override
    public BlockData getNextData() {
        nextVerticalDirection();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "VerticalDirection: " + verticalDirection;
    }

    @Override
    public String getNextAsString() {
        nextVerticalDirection();
        return "VerticalDirection: " + verticalDirection;
    }

    @Override
    public String getDataAsString() {
        return verticalDirection.name();
    }

    @Override
    public String getNextDataAsString() {
        nextVerticalDirection();
        return verticalDirection.name();
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextVerticalDirection(){
        PointedDripstone pointedDripstone = ((PointedDripstone) blockData);
        List<BlockFace> blockFaces = pointedDripstone.getVerticalDirections().stream().toList();
        verticalDirection = blockFaces.get((blockFaces.indexOf(verticalDirection) + 1) % blockFaces.size());
        pointedDripstone.setVerticalDirection(verticalDirection);
    }
}
