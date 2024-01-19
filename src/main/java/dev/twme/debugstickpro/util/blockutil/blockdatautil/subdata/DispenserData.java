package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Dispenser;

public class DispenserData implements SubBlockData{
    private String NAME = "Triggered";
    private BlockData blockData;
    private boolean triggered;
    public DispenserData(BlockData blockData){
        this.blockData = blockData;
        this.triggered = ((Dispenser) blockData).isTriggered();
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
        nextTriggeredProperty();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Triggered: " + triggered;
    }

    @Override
    public String getNextAsString() {
        nextTriggeredProperty();
        return "Triggered: " + triggered;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(triggered);
    }

    @Override
    public String getNextDataAsString() {
        nextTriggeredProperty();
        return String.valueOf(triggered);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextTriggeredProperty(){
        Dispenser dispenser = ((Dispenser) blockData);
        dispenser.setTriggered(!dispenser.isTriggered());
        this.triggered = dispenser.isTriggered();
    }
}
