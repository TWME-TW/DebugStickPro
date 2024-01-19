package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.BubbleColumn;
import org.bukkit.block.data.type.Campfire;

public class CampfireData implements SubBlockData{
    private String NAME = "Campfire";
    private BlockData blockData;
    private boolean isSignalFire;
    public CampfireData(BlockData blockData){
        this.blockData = blockData;
        this.isSignalFire = ((Campfire)blockData).isSignalFire();
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
        nextSignalFireProperty();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Signal Fire: " + isSignalFire;
    }

    @Override
    public String getNextAsString() {
        nextSignalFireProperty();
        return "Signal Fire: " + isSignalFire;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(isSignalFire);
    }

    @Override
    public String getNextDataAsString() {
        nextSignalFireProperty();
        return String.valueOf(isSignalFire);
    }

    private void nextSignalFireProperty(){
        Campfire campfire = ((Campfire) blockData);
        if (campfire.isSignalFire()){
            campfire.setSignalFire(false);
        } else {
            campfire.setSignalFire(true);
        }
        this.isSignalFire = campfire.isSignalFire();
    }
}