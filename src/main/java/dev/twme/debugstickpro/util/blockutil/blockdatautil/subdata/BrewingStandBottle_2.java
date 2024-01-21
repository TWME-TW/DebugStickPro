package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.BrewingStand;

public class BrewingStandBottle_2 implements SubBlockData {
    private BlockData blockData;
    private boolean bottle;
    private boolean isUsing = false;

    public BrewingStandBottle_2(BlockData blockData) {
        this.blockData = blockData;
        this.bottle = ((BrewingStand) blockData).hasBottle(2);
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
        return LangFile.BrewingStandBottle_2.replace("%data%", getDataAsString());
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

    public SubBlockData nextData() {
        BrewingStand brewingStand = ((BrewingStand) blockData);

        brewingStand.setBottle(2, !brewingStand.hasBottle(2));
        this.bottle = brewingStand.hasBottle(2);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((BrewingStand) blockData).setBottle(2, bottle);
        return blockData;
    }
}
