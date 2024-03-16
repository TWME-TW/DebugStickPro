package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
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
    public String dataName() {
        return LangFile.DataKeyName.BrewingStandBottle_2DataName;
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
