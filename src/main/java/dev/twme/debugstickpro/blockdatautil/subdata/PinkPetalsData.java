package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.PinkPetals;

public class PinkPetalsData extends SubBlockData {
    private int flowerAmount;

    public PinkPetalsData(BlockData blockData) {
        this.blockData = blockData;
        this.flowerAmount = ((PinkPetals) blockData).getFlowerAmount();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.PinkPetalsDataname;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(flowerAmount);
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
    public SubBlockData previousData() {
        PinkPetals pinkPetals = ((PinkPetals) blockData);
        if (flowerAmount <= 0) {
            flowerAmount = pinkPetals.getMaximumFlowerAmount();
        } else {
            flowerAmount--;
        }
        pinkPetals.setFlowerAmount(flowerAmount);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((PinkPetals) blockData).setFlowerAmount(flowerAmount);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new PinkPetalsData(blockData);
    }
}
