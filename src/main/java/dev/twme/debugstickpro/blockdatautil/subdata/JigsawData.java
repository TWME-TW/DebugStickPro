package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Jigsaw;

public class JigsawData implements SubBlockData {
    private final BlockData blockData;
    private Jigsaw.Orientation orientation;
    private boolean isUsing = false;

    public JigsawData(BlockData blockData) {
        this.blockData = blockData;
        this.orientation = ((Jigsaw) blockData).getOrientation();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.JigsawDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return orientation.name();
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
        if (orientation == Jigsaw.Orientation.DOWN_EAST) {
            ((Jigsaw) blockData).setOrientation(Jigsaw.Orientation.DOWN_NORTH);
        } else if (orientation == Jigsaw.Orientation.DOWN_NORTH) {
            ((Jigsaw) blockData).setOrientation(Jigsaw.Orientation.DOWN_SOUTH);
        } else if (orientation == Jigsaw.Orientation.DOWN_SOUTH) {
            ((Jigsaw) blockData).setOrientation(Jigsaw.Orientation.DOWN_WEST);
        } else if (orientation == Jigsaw.Orientation.DOWN_WEST) {
            ((Jigsaw) blockData).setOrientation(Jigsaw.Orientation.EAST_UP);
        } else if (orientation == Jigsaw.Orientation.EAST_UP) {
            ((Jigsaw) blockData).setOrientation(Jigsaw.Orientation.NORTH_UP);
        } else if (orientation == Jigsaw.Orientation.NORTH_UP) {
            ((Jigsaw) blockData).setOrientation(Jigsaw.Orientation.SOUTH_UP);
        } else if (orientation == Jigsaw.Orientation.SOUTH_UP) {
            ((Jigsaw) blockData).setOrientation(Jigsaw.Orientation.UP_EAST);
        } else if (orientation == Jigsaw.Orientation.UP_EAST) {
            ((Jigsaw) blockData).setOrientation(Jigsaw.Orientation.UP_NORTH);
        } else if (orientation == Jigsaw.Orientation.UP_NORTH) {
            ((Jigsaw) blockData).setOrientation(Jigsaw.Orientation.UP_SOUTH);
        } else if (orientation == Jigsaw.Orientation.UP_SOUTH) {
            ((Jigsaw) blockData).setOrientation(Jigsaw.Orientation.UP_WEST);
        } else if (orientation == Jigsaw.Orientation.UP_WEST) {
            ((Jigsaw) blockData).setOrientation(Jigsaw.Orientation.WEST_UP);
        } else {
            ((Jigsaw) blockData).setOrientation(Jigsaw.Orientation.DOWN_EAST);
        }
        orientation = ((Jigsaw) blockData).getOrientation();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Jigsaw jigsaw = ((Jigsaw) blockData);

        if (orientation == Jigsaw.Orientation.WEST_UP) {
            orientation = Jigsaw.Orientation.UP_WEST;
        } else if (orientation == Jigsaw.Orientation.UP_WEST) {
            orientation = Jigsaw.Orientation.UP_SOUTH;
        } else if (orientation == Jigsaw.Orientation.UP_SOUTH) {
            orientation = Jigsaw.Orientation.UP_NORTH;
        } else if (orientation == Jigsaw.Orientation.UP_NORTH) {
            orientation = Jigsaw.Orientation.UP_EAST;
        } else if (orientation == Jigsaw.Orientation.UP_EAST) {
            orientation = Jigsaw.Orientation.SOUTH_UP;
        } else if (orientation == Jigsaw.Orientation.SOUTH_UP) {
            orientation = Jigsaw.Orientation.NORTH_UP;
        } else if (orientation == Jigsaw.Orientation.NORTH_UP) {
            orientation = Jigsaw.Orientation.EAST_UP;
        } else if (orientation == Jigsaw.Orientation.EAST_UP) {
            orientation = Jigsaw.Orientation.DOWN_WEST;
        } else if (orientation == Jigsaw.Orientation.DOWN_WEST) {
            orientation = Jigsaw.Orientation.DOWN_SOUTH;
        } else if (orientation == Jigsaw.Orientation.DOWN_SOUTH) {
            orientation = Jigsaw.Orientation.DOWN_NORTH;
        } else if (orientation == Jigsaw.Orientation.DOWN_NORTH) {
            orientation = Jigsaw.Orientation.DOWN_EAST;
        } else {
            orientation = Jigsaw.Orientation.WEST_UP;
        }

        jigsaw.setOrientation(orientation);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Jigsaw) blockData).setOrientation(orientation);
        return blockData;
    }

    @Override
    public SubBlockData getDataFac(BlockData blockData) {
        return new JigsawData(blockData);
    }
}
