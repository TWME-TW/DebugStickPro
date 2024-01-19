package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Door;

public class DoorData implements SubBlockData{
    private String NAME = "Door Hinge";
    private BlockData blockData;
    private Door.Hinge hinge;
    public DoorData(BlockData blockData){
        this.blockData = blockData;
        this.hinge = ((Door) blockData).getHinge();
    }

    @Override
    public String NAME() {
        return NAME;
    }

    @Override
    public BlockData getData() {
        return blockData;
    }

    @Override
    public BlockData getNextData() {
        nextHingeProperty();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Door Hinge: " + hinge;
    }

    @Override
    public String getNextAsString() {
        nextHingeProperty();
        return "Door Hinge: " + hinge;
    }

    @Override
    public String getDataAsString() {
        return hinge.name();
    }

    @Override
    public String getNextDataAsString() {
        nextHingeProperty();
        return hinge.name();
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextHingeProperty(){
        Door door = ((Door) blockData);
        if (door.getHinge() == Door.Hinge.LEFT){
            door.setHinge(Door.Hinge.RIGHT);
        } else {
            door.setHinge(Door.Hinge.LEFT);
        }
        this.hinge = door.getHinge();
    }
}
