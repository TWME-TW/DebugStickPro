package dev.twme.debugstickpro.blockdatautil.subdata.brewingstandbottle;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.BrewingStand;

public abstract class BrewingStandBottle extends SubBlockData {
    private boolean bottle;
    private final int bottleIndex;

    public BrewingStandBottle(BlockData blockData, int bottleIndex) {
        this.blockData = blockData;
        this.bottleIndex = bottleIndex;
        this.bottle = ((BrewingStand) blockData).hasBottle(bottleIndex);
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(bottle);
    }

    @Override
    public SubBlockData nextData() {
        BrewingStand brewingStand = ((BrewingStand) blockData);

        brewingStand.setBottle(bottleIndex, !brewingStand.hasBottle(bottleIndex));
        this.bottle = brewingStand.hasBottle(bottleIndex);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((BrewingStand) blockData).setBottle(bottleIndex, bottle);
        return blockData;
    }
}
