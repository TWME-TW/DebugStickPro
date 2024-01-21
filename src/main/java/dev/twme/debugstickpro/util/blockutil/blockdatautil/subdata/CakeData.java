package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Cake;

public class CakeData implements SubBlockData{
    private String NAME = "Cake";
    private BlockData blockData;
    private int bites;
    public CakeData(BlockData blockData){
        this.blockData = blockData;
        this.bites = ((Cake)blockData).getBites();
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
        nextBite();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Bites: " + bites;
    }

    @Override
    public String getNextAsString() {
        nextBite();
        return "Bites: " + bites;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(bites);
    }

    @Override
    public String getNextDataAsString() {
        nextBite();
        return String.valueOf(bites);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextBite(){
        Cake cake = ((Cake) blockData);
        if (cake.getBites() >= (cake.getMaximumBites() - 1)){
            cake.setBites(0);
        } else {
            cake.setBites(cake.getBites() + 1);
        }
        this.bites = cake.getBites();

    }
}
