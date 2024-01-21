package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Gate;

public class GateData implements SubBlockData{
    private final String NAME = "InWall";
    private BlockData blockData;
    private boolean inWall;
    public GateData(BlockData blockData){
        this.blockData = blockData;
        this.inWall = ((Gate) blockData).isInWall();
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
        nextInWallProperty();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "InWall: " + inWall;
    }

    @Override
    public String getNextAsString() {
        nextInWallProperty();
        return "InWall: " + inWall;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(inWall);
    }

    @Override
    public String getNextDataAsString() {
        nextInWallProperty();
        return String.valueOf(inWall);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextInWallProperty(){
        Gate gate = ((org.bukkit.block.data.type.Gate) blockData);
        gate.setInWall(!gate.isInWall());
        this.inWall = gate.isInWall();
    }
}
