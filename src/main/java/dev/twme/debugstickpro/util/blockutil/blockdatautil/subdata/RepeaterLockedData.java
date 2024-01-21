package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Repeater;

public class RepeaterLockedData implements SubBlockData{
    private String NAME = "RepeaterLocked";
    private BlockData blockData;
    private boolean locked;
    public RepeaterLockedData(BlockData blockData) {
        this.blockData = blockData;
        this.locked = ((Repeater) blockData).isLocked();
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
        nextData();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Locked: " + locked;
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "Locked: " + locked;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(locked);
    }

    @Override
    public String getNextDataAsString() {
        nextData();
        return String.valueOf(locked);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData(){
        Repeater repeater = ((Repeater) blockData);
        locked = !locked;
        repeater.setLocked(locked);
    }
}
