package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.BrewingStand;

public class BrewingStandBottle_0 implements SubBlockData {
    private BlockData blockData;
    private boolean bottle;
    private boolean isUsing = false;

    public BrewingStandBottle_0(BlockData blockData) {
        this.blockData = blockData;
        this.bottle = ((BrewingStand) blockData).hasBottle(0);
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.BrewingStandBottle_0DataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(bottle);
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
        BrewingStand brewingStand = ((BrewingStand) blockData);

        brewingStand.setBottle(0, !brewingStand.hasBottle(0));
        this.bottle = brewingStand.hasBottle(0);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((BrewingStand) blockData).setBottle(0, bottle);
        return blockData;
    }
}
