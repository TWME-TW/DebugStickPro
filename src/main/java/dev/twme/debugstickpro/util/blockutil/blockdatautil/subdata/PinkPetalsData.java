package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.PinkPetals;

public class PinkPetalsData implements SubBlockData{
    private String NAME = "PinkPetals";
    private BlockData blockData;
    private int flowerAmount;
    public PinkPetalsData(BlockData blockData){
        this.blockData = blockData;
        this.flowerAmount = ((PinkPetals) blockData).getFlowerAmount();
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
        nextFlowerAmount();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "FlowerAmount: " + flowerAmount;
    }

    @Override
    public String getNextAsString() {
        nextFlowerAmount();
        return "FlowerAmount: " + flowerAmount;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(flowerAmount);
    }

    @Override
    public String getNextDataAsString() {
        nextFlowerAmount();
        return String.valueOf(flowerAmount);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextFlowerAmount(){
        PinkPetals pinkPetals = ((PinkPetals) blockData);
        if (flowerAmount >= pinkPetals.getMaximumFlowerAmount()) {
            flowerAmount = 0;
        } else {
            flowerAmount++;
        }
        pinkPetals.setFlowerAmount(flowerAmount);
    }
}
