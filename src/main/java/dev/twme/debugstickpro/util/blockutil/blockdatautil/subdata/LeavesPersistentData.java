package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Leaves;

public class LeavesPersistentData implements SubBlockData{
    private String NAME = "Leaves Persistent";
    private BlockData blockData;
    private boolean persistent;
    private boolean isUsing;
    public LeavesPersistentData(BlockData blockData){
        this.blockData = blockData;
        this.persistent = ((Leaves) blockData).isPersistent();
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
        return "Persistent: " + persistent;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(persistent);
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

    public SubBlockData nextData(){
        ((Leaves) blockData).setPersistent(!persistent);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Leaves)blockData).setPersistent(persistent);
        return blockData;
    }
}
