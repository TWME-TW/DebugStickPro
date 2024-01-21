package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Crafter;

public class CrafterOrientationData implements SubBlockData{
    private String NAME = "Crafter Orientation";
    private BlockData blockData;
    private Crafter.Orientation orientation;
    public CrafterOrientationData(BlockData blockData){
        this.blockData = blockData;
        this.orientation = ((Crafter) blockData).getOrientation();
    }
    @Override
    public String name() {
        return NAME;
    }

    @Override
    public BlockData getData() {
        return blockData;
    }

    @Override
    public BlockData getNextData() {
        nextOrientationProperty();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Crafter Orientation: " + orientation.name();
    }

    @Override
    public String getNextAsString() {
        nextOrientationProperty();
        return "Crafter Orientation: " + orientation.name();
    }

    @Override
    public String getDataAsString() {
        return orientation.name();
    }

    @Override
    public String getNextDataAsString() {
        nextOrientationProperty();
        return orientation.name();
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextOrientationProperty(){
        Crafter crafter = ((Crafter) blockData);
        if (crafter.getOrientation() == Crafter.Orientation.DOWN_EAST){
            crafter.setOrientation(Crafter.Orientation.DOWN_NORTH);
        } else if (crafter.getOrientation() == Crafter.Orientation.DOWN_NORTH){
            crafter.setOrientation(Crafter.Orientation.DOWN_SOUTH);
        } else if (crafter.getOrientation() == Crafter.Orientation.DOWN_SOUTH){
            crafter.setOrientation(Crafter.Orientation.DOWN_WEST);
        } else if (crafter.getOrientation() == Crafter.Orientation.DOWN_WEST){
            crafter.setOrientation(Crafter.Orientation.EAST_UP);
        } else if (crafter.getOrientation() == Crafter.Orientation.EAST_UP){
            crafter.setOrientation(Crafter.Orientation.NORTH_UP);
        } else if (crafter.getOrientation() == Crafter.Orientation.NORTH_UP){
            crafter.setOrientation(Crafter.Orientation.SOUTH_UP);
        } else if (crafter.getOrientation() == Crafter.Orientation.SOUTH_UP){
            crafter.setOrientation(Crafter.Orientation.UP_EAST);
        } else if (crafter.getOrientation() == Crafter.Orientation.UP_EAST){
            crafter.setOrientation(Crafter.Orientation.UP_NORTH);
        } else if (crafter.getOrientation() == Crafter.Orientation.UP_NORTH){
            crafter.setOrientation(Crafter.Orientation.UP_SOUTH);
        } else if (crafter.getOrientation() == Crafter.Orientation.UP_SOUTH){
            crafter.setOrientation(Crafter.Orientation.UP_WEST);
        } else if (crafter.getOrientation() == Crafter.Orientation.UP_WEST){
            crafter.setOrientation(Crafter.Orientation.WEST_UP);
        } else if (crafter.getOrientation() == Crafter.Orientation.WEST_UP){
            crafter.setOrientation(Crafter.Orientation.DOWN_EAST);
        }
        this.orientation = crafter.getOrientation();
    }
}
