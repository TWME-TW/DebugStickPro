package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.block.data.BlockData;

public class AnaloguePowerableData implements SubBlockData {
    private final BlockData blockData;
    private int power;
    private boolean isUsing = false;

    public AnaloguePowerableData(BlockData blockData) {
        this.blockData = blockData;
        this.power = ((AnaloguePowerable) blockData).getPower();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.AnaloguePowerableDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(power);
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
    public BlockData copyTo(BlockData blockData) {
        ((AnaloguePowerable) blockData).setPower(power);
        return blockData;
    }

    @Override
    public SubBlockData getDataFac(BlockData blockData) {
        return new AnaloguePowerableData(blockData);
    }

    @Override
    public SubBlockData nextData() {
        AnaloguePowerable analoguePowerable = (AnaloguePowerable) blockData;
        if (analoguePowerable.getPower() >= analoguePowerable.getMaximumPower()) {
            analoguePowerable.setPower(0);
        } else {
            analoguePowerable.setPower(analoguePowerable.getPower() + 1);
        }
        this.power = analoguePowerable.getPower();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        AnaloguePowerable analoguePowerable = (AnaloguePowerable) blockData;
        if (analoguePowerable.getPower() <= 0) {
            analoguePowerable.setPower(analoguePowerable.getMaximumPower());
        } else {
            analoguePowerable.setPower(analoguePowerable.getPower() - 1);
        }
        this.power = analoguePowerable.getPower();
        return this;
    }
}
