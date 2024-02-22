package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Wall;

public class WallHeightEastData implements SubBlockData {
    private BlockData blockData;
    private Wall.Height height;
    private boolean isUsing = false;

    public WallHeightEastData(BlockData blockData) {
        this.blockData = blockData;
        this.height = ((Wall) blockData).getHeight(BlockFace.EAST);
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.WallHeightEastDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
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
        if (height == Wall.Height.NONE) {
            height = Wall.Height.LOW;
        } else if (height == Wall.Height.LOW) {
            height = Wall.Height.TALL;
        } else if (height == Wall.Height.TALL) {
            height = Wall.Height.NONE;
        }
        wall.setHeight(BlockFace.EAST, height);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Wall) blockData).setHeight(BlockFace.EAST, height);
        return blockData;
    }
}