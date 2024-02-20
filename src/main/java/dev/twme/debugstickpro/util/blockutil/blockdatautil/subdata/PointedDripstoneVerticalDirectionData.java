package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.PointedDripstone;

import java.util.List;

public class PointedDripstoneVerticalDirectionData implements SubBlockData{
    private final String NAME = "PointedDripstoneVerticalDirection";
    private BlockData blockData;
    private BlockFace verticalDirection;
    private boolean isUsing = false;
    public PointedDripstoneVerticalDirectionData(BlockData blockData) {
        this.blockData = blockData;
        this.verticalDirection = ((PointedDripstone) blockData).getVerticalDirection();
    }
    @Override
    public String name() {
        return NAME;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getAsString() {
        return "VerticalDirection: " + verticalDirection;
    }



    @Override
    public String getDataAsString() {
        return verticalDirection.name();
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

    public SubBlockData nextData(){
        PointedDripstone pointedDripstone = ((PointedDripstone) blockData);
        List<BlockFace> blockFaces = pointedDripstone.getVerticalDirections().stream().toList();
        verticalDirection = blockFaces.get((blockFaces.indexOf(verticalDirection) + 1) % blockFaces.size());
        pointedDripstone.setVerticalDirection(verticalDirection);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((PointedDripstone)blockData).setVerticalDirection(verticalDirection);
        return blockData;
    }
}
