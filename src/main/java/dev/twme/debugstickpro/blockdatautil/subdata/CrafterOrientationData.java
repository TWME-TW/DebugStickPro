package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Crafter;

public class CrafterOrientationData implements SubBlockData {
    private BlockData blockData;
    private Crafter.Orientation orientation;
    private boolean isUsing;

    public CrafterOrientationData(BlockData blockData) {
        this.blockData = blockData;
        this.orientation = ((Crafter) blockData).getOrientation();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.CrafterOrientationDataName;
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
        Crafter crafter = ((Crafter) blockData);
        if (crafter.getOrientation() == Crafter.Orientation.DOWN_EAST) {
            crafter.setOrientation(Crafter.Orientation.DOWN_NORTH);
        } else if (crafter.getOrientation() == Crafter.Orientation.DOWN_NORTH) {
            crafter.setOrientation(Crafter.Orientation.DOWN_SOUTH);
        } else if (crafter.getOrientation() == Crafter.Orientation.DOWN_SOUTH) {
            crafter.setOrientation(Crafter.Orientation.DOWN_WEST);
        } else if (crafter.getOrientation() == Crafter.Orientation.DOWN_WEST) {
            crafter.setOrientation(Crafter.Orientation.EAST_UP);
        } else if (crafter.getOrientation() == Crafter.Orientation.EAST_UP) {
            crafter.setOrientation(Crafter.Orientation.NORTH_UP);
        } else if (crafter.getOrientation() == Crafter.Orientation.NORTH_UP) {
            crafter.setOrientation(Crafter.Orientation.SOUTH_UP);
        } else if (crafter.getOrientation() == Crafter.Orientation.SOUTH_UP) {
            crafter.setOrientation(Crafter.Orientation.UP_EAST);
        } else if (crafter.getOrientation() == Crafter.Orientation.UP_EAST) {
            crafter.setOrientation(Crafter.Orientation.UP_NORTH);
        } else if (crafter.getOrientation() == Crafter.Orientation.UP_NORTH) {
            crafter.setOrientation(Crafter.Orientation.UP_SOUTH);
        } else if (crafter.getOrientation() == Crafter.Orientation.UP_SOUTH) {
            crafter.setOrientation(Crafter.Orientation.UP_WEST);
        } else if (crafter.getOrientation() == Crafter.Orientation.UP_WEST) {
            crafter.setOrientation(Crafter.Orientation.WEST_UP);
        } else if (crafter.getOrientation() == Crafter.Orientation.WEST_UP) {
            crafter.setOrientation(Crafter.Orientation.DOWN_EAST);
        }
        this.orientation = crafter.getOrientation();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Crafter) blockData).setOrientation(orientation);
        return blockData;
    }
}
