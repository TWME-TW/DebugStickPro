package dev.twme.debugstickpro.blockdatautil.subdata.mossycarpetheight;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.MossyCarpet;
import org.bukkit.block.data.type.Wall;

import java.util.List;

public abstract class MossyCarpetHeightData extends SubBlockData {
    private static final List<Wall.Height> heights = List.of(
            Wall.Height.NONE,
            Wall.Height.LOW,
            Wall.Height.TALL
    );

    private final BlockFace face;
    private Wall.Height height;

    public MossyCarpetHeightData(BlockData blockData, BlockFace face) {
        this.blockData = blockData;
        this.face = face;
        this.height = ((MossyCarpet) blockData).getHeight(face);
    }

    @Override
    public String getDataAsString() {
        return height.name();
    }

    @Override
    public SubBlockData nextData() {
        MossyCarpet mossyCarpet = (MossyCarpet) blockData;
        height = heights.get((heights.indexOf(height) + 1) % heights.size());
        mossyCarpet.setHeight(face, height);
        this.blockData = mossyCarpet;
        return this;
    }

    @Override
    public SubBlockData previousData() {
        MossyCarpet mossyCarpet = (MossyCarpet) blockData;
        height = heights.get((heights.indexOf(height) - 1 + heights.size()) % heights.size());
        mossyCarpet.setHeight(face, height);
        this.blockData = mossyCarpet;
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((MossyCarpet) blockData).setHeight(face, height);
        return blockData;
    }
}
