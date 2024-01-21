package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Wall;

public class WallUPData implements SubBlockData{
    private BlockData blockData;
    private boolean up;
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
    public BlockData getNextData() {
        nextData();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Up: " + up;
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "Up: " + up;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(up);
    }

    @Override
    public String getNextDataAsString() {
        nextData();
        return String.valueOf(up);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData(){
        Wall wall = ((Wall) blockData);
        up = !up;
        wall.setUp(up);
    }
}
