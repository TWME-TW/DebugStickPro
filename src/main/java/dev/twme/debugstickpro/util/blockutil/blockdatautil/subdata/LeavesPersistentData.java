package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Leaves;

public class LeavesPersistentData implements SubBlockData{
    private String NAME = "Leaves Persistent";
    private BlockData blockData;
    private boolean persistent;
    public LeavesPersistentData(BlockData blockData){
        this.blockData = blockData;
        this.persistent = ((Leaves) blockData).isPersistent();
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
        nextPersistentProperty();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Persistent: " + persistent;
    }

    @Override
    public String getNextAsString() {
        nextPersistentProperty();
        return "Persistent: " + persistent;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(persistent);
    }

    @Override
    public String getNextDataAsString() {
        return String.valueOf(persistent);
    }
    private void nextPersistentProperty(){
        ((Leaves) blockData).setPersistent(!persistent);
    }
}
