package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.PinkPetals;

public class PinkPetalsData implements SubBlockData {
    private BlockData blockData;
    private int flowerAmount;
    private boolean isUsing = false;

    public PinkPetalsData(BlockData blockData) {
        this.blockData = blockData;
        this.flowerAmount = ((PinkPetals) blockData).getFlowerAmount();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.PinkPetalsDataname;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(flowerAmount);
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

    @Override
    public SubBlockData nextData() {
        PinkPetals pinkPetals = ((PinkPetals) blockData);
        if (flowerAmount >= pinkPetals.getMaximumFlowerAmount()) {
            flowerAmount = 0;
        } else {
            flowerAmount++;
        }
        pinkPetals.setFlowerAmount(flowerAmount);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((PinkPetals) blockData).setFlowerAmount(flowerAmount);
        return blockData;
    }
}
