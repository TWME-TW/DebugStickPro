package dev.twme.debugstickpro.blockdatautil.subdata.wallheight;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Wall;

import java.util.List;

public abstract class WallHeightData extends SubBlockData {
    private final BlockFace face;
    private Wall.Height height;

    private final static List<Wall.Height> heights = List.of(
            Wall.Height.NONE,
            Wall.Height.LOW,
            Wall.Height.TALL
    );

    public WallHeightData(BlockData blockData, BlockFace face) {
        this.blockData = blockData;
        this.face = face;
        this.height = ((Wall) blockData).getHeight(face);
    }

    @Override
    public String getDataAsString() {
        return height.name();
    }

    @Override
    public SubBlockData nextData() {
        Wall wall = (Wall) blockData;
        height = heights.get((heights.indexOf(height) + 1) % heights.size());
        wall.setHeight(face, height);
        this.blockData = wall;
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Wall wall = (Wall) blockData;
        height = heights.get((heights.indexOf(height) + 2) % heights.size());
        wall.setHeight(face, height);
        this.blockData = wall;
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Wall) blockData).setHeight(face, height);
        return blockData;
    }
}
