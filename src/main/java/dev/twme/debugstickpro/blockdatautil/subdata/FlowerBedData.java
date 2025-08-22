package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.FlowerBed;

public class FlowerBedData extends SubBlockData {
    private int flowerAmount;

    public FlowerBedData(BlockData blockData) {
        this.blockData = blockData;
        this.flowerAmount = ((FlowerBed) blockData).getFlowerAmount();
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
        FlowerBed flowerBeds = ((FlowerBed) blockData);
        if (flowerAmount >= flowerBeds.getMaximumFlowerAmount()) {
            flowerAmount = 0;
        } else {
            flowerAmount++;
        }
        flowerBeds.setFlowerAmount(flowerAmount);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        FlowerBed flowerBeds = ((FlowerBed) blockData);
        if (flowerAmount <= 0) {
            flowerAmount = flowerBeds.getMaximumFlowerAmount();
        } else {
            flowerAmount--;
        }
        flowerBeds.setFlowerAmount(flowerAmount);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((FlowerBed) blockData).setFlowerAmount(flowerAmount);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new FlowerBedData(blockData);
    }
}
