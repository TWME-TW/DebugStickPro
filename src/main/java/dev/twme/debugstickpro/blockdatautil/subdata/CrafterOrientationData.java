package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Crafter;
import org.bukkit.entity.LightningStrike;

import java.util.List;

public class CrafterOrientationData extends SubBlockData {
    private Crafter.Orientation orientation;
    private final static List<Crafter.Orientation> orientations = List.of(
                    Crafter.Orientation.DOWN_EAST
                    , Crafter.Orientation.DOWN_NORTH
                    , Crafter.Orientation.DOWN_SOUTH
                    , Crafter.Orientation.DOWN_WEST
                    , Crafter.Orientation.EAST_UP
                    , Crafter.Orientation.NORTH_UP
                    , Crafter.Orientation.SOUTH_UP
                    , Crafter.Orientation.UP_EAST
                    , Crafter.Orientation.UP_NORTH
                    , Crafter.Orientation.UP_SOUTH
                    , Crafter.Orientation.UP_WEST
                    , Crafter.Orientation.WEST_UP);

    public CrafterOrientationData(BlockData blockData) {
        this.blockData = blockData;
        this.orientation = ((Crafter) blockData).getOrientation();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.CrafterOrientationDataName;
    }

    @Override
    public String getDataAsString() {
        return orientation.name();
    }

    @Override
    public SubBlockData nextData() {
        Crafter.Orientation orientation = ((Crafter) blockData).getOrientation();
        this.orientation = orientations.get((orientations.indexOf(orientation) + 1) % orientations.size());
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Crafter.Orientation orientation = ((Crafter) blockData).getOrientation();
        this.orientation = orientations.get((orientations.indexOf(orientation) - 1 + orientations.size()) % orientations.size());
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Crafter) blockData).setOrientation(orientation);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new CrafterOrientationData(blockData);
    }
}
