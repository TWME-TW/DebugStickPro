package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Tripwire;

public class TripwireData implements SubBlockData{
    private BlockData blockData;
    private boolean isDisarmed;
    public TripwireData(BlockData blockData){
        this.blockData = blockData;
        this.isDisarmed = ((Tripwire)blockData).isDisarmed();
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
    public BlockData getNextData() {
        nextData();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Disarmed: " + isDisarmed;
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "Disarmed: " + isDisarmed;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(isDisarmed);
    }

    @Override
    public String getNextDataAsString() {
        nextData();
        return String.valueOf(isDisarmed);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData(){
        Tripwire tripwire = ((Tripwire) blockData);
        isDisarmed = !isDisarmed;
        tripwire.setDisarmed(isDisarmed);
    }
}
