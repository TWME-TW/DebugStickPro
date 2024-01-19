package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.BrewingStand;

public class BrewingStandBottle_1 implements SubBlockData {
    private String NAME = "Bottle_1";
    private BlockData blockData;
    private boolean bottle;

    public BrewingStandBottle_1(BlockData blockData) {
        this.blockData = blockData;
        this.bottle = ((BrewingStand) blockData).hasBottle(1);
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
        next();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Bottle_1: " + bottle;
    }

    @Override
    public String getNextAsString() {
        next();
        return "Bottle_1: " + bottle;
    }

    @Override
    public String getDataAsString(BlockData blockData) {
        return String.valueOf(bottle);
    }

    @Override
    public String getNextDataAsString() {
        next();
        return String.valueOf(bottle);
    }

    private void next() {
        BrewingStand brewingStand = ((BrewingStand) blockData);

        if (brewingStand.hasBottle(1)) {
            brewingStand.setBottle(1, false);
        } else {
            brewingStand.setBottle(1, true);
        }
        this.bottle = brewingStand.hasBottle(1);
    }
}
