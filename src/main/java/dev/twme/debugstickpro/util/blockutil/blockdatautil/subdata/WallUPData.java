package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Wall;

public class WallUPData implements SubBlockData{
    private BlockData blockData;
    private boolean up;
    private boolean isUsing = false;
    public WallUPData(BlockData blockData){
        this.blockData = blockData;
        this.up = ((Wall)blockData).isUp();
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
        return "Up: " + up;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(up);
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
        Wall wall = ((Wall) blockData);
        up = !up;
        wall.setUp(up);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Wall)blockData).setUp(up);
        return blockData;
    }
}
