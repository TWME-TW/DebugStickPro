package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Wall;

public class WallHeightNorthData implements SubBlockData{
    private BlockData blockData;
    private Wall.Height height;
    private boolean isUsing = false;
    public WallHeightNorthData(BlockData blockData){
        this.blockData = blockData;
        this.height = ((Wall)blockData).getHeight(BlockFace.NORTH);
    }
    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }

    @Override
    public String getAsString() {
        return "north: " + height.name();
    }

    @Override
    public String getDataAsString() {
        return height.name();
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
        Wall wall = ((Wall) blockData);
        if (height == Wall.Height.NONE){
            height = Wall.Height.LOW;
        } else if (height == Wall.Height.LOW){
            height = Wall.Height.TALL;
        } else if (height == Wall.Height.TALL){
            height = Wall.Height.NONE;
        }
        wall.setHeight(BlockFace.NORTH, height);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Wall)blockData).setHeight(BlockFace.NORTH, height);
        return blockData;
    }
}
