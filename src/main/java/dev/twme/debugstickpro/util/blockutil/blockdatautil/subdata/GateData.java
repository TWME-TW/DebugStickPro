package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Gate;

public class GateData implements SubBlockData{
    private final String NAME = "InWall";
    private BlockData blockData;
    private boolean inWall;
    private boolean isUsing = false;
    public GateData(BlockData blockData){
        this.blockData = blockData;
        this.inWall = ((Gate) blockData).isInWall();
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }



    @Override
    public String getAsString() {
        return "InWall: " + inWall;
    }



    @Override
    public String getDataAsString() {
        return String.valueOf(inWall);
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
    public SubBlockData nextData(){
        Gate gate = ((org.bukkit.block.data.type.Gate) blockData);
        gate.setInWall(!gate.isInWall());
        this.inWall = gate.isInWall();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Gate) blockData).setInWall(inWall);
        return blockData;
    }
}
