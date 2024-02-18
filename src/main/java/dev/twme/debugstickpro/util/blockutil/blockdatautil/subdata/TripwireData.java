package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Tripwire;

public class TripwireData implements SubBlockData {
    private BlockData blockData;
    private boolean isDisarmed;
    private boolean isUsing = false;

    public TripwireData(BlockData blockData) {
        this.blockData = blockData;
        this.isDisarmed = ((Tripwire) blockData).isDisarmed();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public BlockData getData() {
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Disarmed: " + isDisarmed;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(isDisarmed);
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

    public SubBlockData nextData() {
        Tripwire tripwire = ((Tripwire) blockData);
        isDisarmed = !isDisarmed;
        tripwire.setDisarmed(isDisarmed);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Tripwire) blockData).setDisarmed(isDisarmed);
        return blockData;
    }
}
