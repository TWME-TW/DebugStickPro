package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Repeater;

public class RepeaterDelayData implements SubBlockData{
    private String NAME = "RepeaterDelay";
    private BlockData blockData;
    private int delay;
    private boolean isUsing = false;

    public RepeaterDelayData(BlockData blockData) {
        this.blockData = blockData;
        this.delay = ((Repeater) blockData).getDelay();
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
    public String getAsString() {
        return "Delay: " + delay + " ticks";
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(delay);
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
        Repeater repeater = ((Repeater) blockData);
        delay = repeater.getDelay();
        if(delay >= repeater.getMaximumDelay()){
            delay = repeater.getMinimumDelay();
        }else{
            delay++;
        }
        repeater.setDelay(delay);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Repeater)blockData).setDelay(delay);
        return blockData;
    }
}
