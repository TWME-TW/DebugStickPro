package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.TurtleEgg;

public class TurtleEggData implements SubBlockData{
    private BlockData blockData;
    private int eggs;
    public TurtleEggData(BlockData blockData){
        this.blockData = blockData;
        this.eggs = ((TurtleEgg)blockData).getEggs();
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
        return "Eggs: " + eggs;
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "Eggs: " + eggs;
    }

    @Override
    public String getDataAsString() {
        return null;
    }

    @Override
    public String getNextDataAsString() {
        return null;
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData(){
        TurtleEgg turtleEgg = ((TurtleEgg) blockData);
        if (eggs >= turtleEgg.getMaximumEggs()){
            eggs = turtleEgg.getMinimumEggs();
        } else {
            eggs++;
        }
        turtleEgg.setEggs(eggs);
    }
}
