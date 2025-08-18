package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.Orientation;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Crafter;
import org.bukkit.entity.LightningStrike;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CrafterOrientationData extends SubBlockData {
    private @NotNull Orientation orientation;
    private final static List<Orientation> orientations = List.of(
                    Orientation.DOWN_EAST
                    , Orientation.DOWN_NORTH
                    , Orientation.DOWN_SOUTH
                    , Orientation.DOWN_WEST
                    , Orientation.EAST_UP
                    , Orientation.NORTH_UP
                    , Orientation.SOUTH_UP
                    , Orientation.UP_EAST
                    , Orientation.UP_NORTH
                    , Orientation.UP_SOUTH
                    , Orientation.UP_WEST
                    , Orientation.WEST_UP);

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
        @NotNull Orientation orientation = ((Crafter) blockData).getOrientation();
        this.orientation = orientations.get((orientations.indexOf(orientation) + 1) % orientations.size());
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Orientation orientation = ((Crafter) blockData).getOrientation();
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
