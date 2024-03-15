package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.PointedDripstone;

import java.util.List;

public class PointedDripstoneVerticalDirectionData implements SubBlockData {
    private BlockData blockData;
    private BlockFace verticalDirection;
    private boolean isUsing = false;

    public PointedDripstoneVerticalDirectionData(BlockData blockData) {
        this.blockData = blockData;
        this.verticalDirection = ((PointedDripstone) blockData).getVerticalDirection();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.PointedDripstoneVerticalDirectionDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
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

    public SubBlockData nextData() {
        PointedDripstone pointedDripstone = ((PointedDripstone) blockData);
        List<BlockFace> blockFaces = pointedDripstone.getVerticalDirections().stream().toList();
        verticalDirection = blockFaces.get((blockFaces.indexOf(verticalDirection) + 1) % blockFaces.size());
        pointedDripstone.setVerticalDirection(verticalDirection);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((PointedDripstone) blockData).setVerticalDirection(verticalDirection);
        return blockData;
    }
}
