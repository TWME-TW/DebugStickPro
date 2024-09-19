package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Jigsaw;

import java.util.List;

public class JigsawData extends SubBlockData {
    private Jigsaw.Orientation orientation;
    private final static List<Jigsaw.Orientation> orientations = List.of(
            Jigsaw.Orientation.DOWN_EAST
            , Jigsaw.Orientation.DOWN_NORTH
            , Jigsaw.Orientation.DOWN_SOUTH
            , Jigsaw.Orientation.DOWN_WEST
            , Jigsaw.Orientation.EAST_UP
            , Jigsaw.Orientation.NORTH_UP
            , Jigsaw.Orientation.SOUTH_UP
            , Jigsaw.Orientation.UP_EAST
            , Jigsaw.Orientation.UP_NORTH
            , Jigsaw.Orientation.UP_SOUTH
            , Jigsaw.Orientation.UP_WEST
            , Jigsaw.Orientation.WEST_UP);

    public JigsawData(BlockData blockData) {
        this.blockData = blockData;
        this.orientation = ((Jigsaw) blockData).getOrientation();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.JigsawDataName;
    }

    @Override
    public String getDataAsString() {
        return orientation.name();
    }

    @Override
    public SubBlockData nextData() {
        orientation = orientations.get((orientations.indexOf(orientation) + 1) % orientations.size());
        ((Jigsaw) blockData).setOrientation(orientation);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        orientation = orientations.get((orientations.indexOf(orientation) - 1 + orientations.size()) % orientations.size());
        ((Jigsaw) blockData).setOrientation(orientation);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Jigsaw) blockData).setOrientation(orientation);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new JigsawData(blockData);
    }
}
