package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Door;

public class DoorData implements SubBlockData {
    private final BlockData blockData;
    private Door.Hinge hinge;
    private boolean isUsing = false;

    public DoorData(BlockData blockData) {
        this.blockData = blockData;
        this.hinge = ((Door) blockData).getHinge();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.DoorDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return hinge.name();
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
