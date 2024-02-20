package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.TNT;

public class TNTData implements SubBlockData{
    private BlockData blockData;
    private boolean unstable;
    private boolean isUsing = false;

    public TNTData(BlockData blockData){
        this.blockData = blockData;
        this.unstable = ((TNT)blockData).isUnstable();
    }
    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }



    @Override
    public String getAsString() {
        return "Unstable: " + unstable;
    }



    @Override
    public String getDataAsString() {
        return String.valueOf(unstable);
    }



    @Override
    public SubBlockData setIsUsing(boolean isUsing) {
        this.isUsing = isUsing;
        return  this;
    }

    @Override
    public boolean isUsing() {
        return isUsing;
    }

    public SubBlockData nextData(){
        TNT tnt = ((TNT) blockData);
        unstable = !unstable;
        tnt.setUnstable(unstable);
        return  this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((TNT)blockData).setUnstable(unstable);
        return blockData;
    }
}
