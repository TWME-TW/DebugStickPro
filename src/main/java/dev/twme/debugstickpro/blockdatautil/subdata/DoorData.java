package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Door;

public class DoorData extends SubBlockData {
    private Door.Hinge hinge;

    public DoorData(BlockData blockData) {
        this.blockData = blockData;
        this.hinge = ((Door) blockData).getHinge();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.DoorDataName;
    }

    @Override
    public String getDataAsString() {
        return hinge.name();
    }

    @Override
    public SubBlockData nextData() {
        Door door = ((Door) blockData);
        if (door.getHinge() == Door.Hinge.LEFT) {
            door.setHinge(Door.Hinge.RIGHT);
        } else {
            door.setHinge(Door.Hinge.LEFT);
        }
        this.hinge = door.getHinge();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Door) blockData).setHinge(hinge);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new DoorData(blockData);
    }
}
