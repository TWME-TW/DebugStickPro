package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.TNT;

public class TNTData implements SubBlockData{
    private BlockData blockData;
    private boolean unstable;
    public TNTData(BlockData blockData){
        this.blockData = blockData;
        this.unstable = ((TNT)blockData).isUnstable();
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
        return "Unstable: " + unstable;
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "Unstable: " + unstable;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(unstable);
    }

    @Override
    public String getNextDataAsString() {
        nextData();
        return String.valueOf(unstable);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData(){
        TNT tnt = ((TNT) blockData);
        unstable = !unstable;
        tnt.setUnstable(unstable);
    }
}
