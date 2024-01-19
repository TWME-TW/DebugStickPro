package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.BrewingStand;

public class BrewingStandBottle_2 implements SubBlockData {
    private String NAME = "Bottle_2";
    private BlockData blockData;
    private boolean bottle;

    public BrewingStandBottle_2(BlockData blockData) {
        this.blockData = blockData;
        this.bottle = ((BrewingStand) blockData).hasBottle(2);
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
        return "Bottle_2: " + bottle;
    }

    @Override
    public String getNextAsString() {
        next();
        return "Bottle_2: " + bottle;
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

        if (brewingStand.hasBottle(2)) {
            brewingStand.setBottle(2, false);
        } else {
            brewingStand.setBottle(2, true);
        }
        this.bottle = brewingStand.hasBottle(2);
    }
}
