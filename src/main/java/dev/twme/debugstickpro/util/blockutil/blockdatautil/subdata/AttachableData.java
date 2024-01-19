package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.Attachable;
import org.bukkit.block.data.BlockData;

public class AttachableData implements SubBlockData{
    private final String NAME = "Attached";
    private BlockData blockData;
    boolean isAttached;

    public AttachableData(BlockData blockData) {
        this.blockData = blockData;
        this.isAttached = ((Attachable) blockData).isAttached();
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
        nextAttached();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Attached: " + isAttached;
    }

    @Override
    public String getNextAsString() {
        nextAttached();
        return "Attached: " + isAttached;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(isAttached);
    }

    @Override
    public String getNextDataAsString() {
        return String.valueOf(isAttached);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextAttached() {
        ((Attachable) blockData).setAttached(!isAttached);
        this.isAttached = ((Attachable) blockData).isAttached();
    }
}
