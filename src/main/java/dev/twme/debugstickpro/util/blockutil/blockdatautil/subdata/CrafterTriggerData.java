package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Crafter;

public class CrafterTriggerData implements SubBlockData{
    private String NAME = "Triggered";
    private BlockData blockData;
    private boolean triggered;
    public CrafterTriggerData(BlockData blockData){
        this.blockData = blockData;
        this.triggered = ((Crafter) blockData).isTriggered();
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
        Crafter crafter = ((Crafter) blockData);
        crafter.setTriggered(!crafter.isTriggered());
        this.triggered = crafter.isTriggered();
    }
}
