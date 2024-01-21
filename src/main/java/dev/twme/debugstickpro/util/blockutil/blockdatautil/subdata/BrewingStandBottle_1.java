package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.BrewingStand;

public class BrewingStandBottle_1 implements SubBlockData {
    private BlockData blockData;
    private boolean bottle;
    private boolean isUsing = false;

    public BrewingStandBottle_1(BlockData blockData) {
        this.blockData = blockData;
        this.bottle = ((BrewingStand) blockData).hasBottle(1);
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
    public String getAsString() {
        return LangFile.BrewingStandBottle_1.replace("%data%", getDataAsString());
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(bottle);
    }


    @Override
    public void setIsUsing(boolean isUsing) {
        this.isUsing = isUsing;
    }

    @Override
    public boolean isUsing() {
        return isUsing;
    }

    @Override
    public SubBlockData nextData() {
        BrewingStand brewingStand = ((BrewingStand) blockData);

        brewingStand.setBottle(1, !brewingStand.hasBottle(1));
        this.bottle = brewingStand.hasBottle(1);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((BrewingStand) blockData).setBottle(1, bottle);
        return blockData;
    }
}
