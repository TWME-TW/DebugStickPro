package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.block.data.BlockData;

public class AnaloguePowerableData implements SubBlockData{
    private final String NAME = "Power";
    private BlockData blockData;
    private int power;

    public AnaloguePowerableData(BlockData blockData) {
        this.blockData = blockData;
        this.power = ((AnaloguePowerable) blockData).getPower();
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
        nextPower();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Power: " + power;
    }

    @Override
    public String getNextAsString() {
        nextPower();
        return "Power: " + power;
    }

    @Override
    public String getDataAsString(BlockData blockData) {
        return String.valueOf(power);
    }

    @Override
    public String getNextDataAsString() {
        nextPower();
        return String.valueOf(power);
    }

    private void nextPower() {
        AnaloguePowerable analoguePowerable = (AnaloguePowerable) blockData;
        if (analoguePowerable.getPower() >= analoguePowerable.getMaximumPower()) {
            analoguePowerable.setPower(0);
        } else {
            analoguePowerable.setPower(analoguePowerable.getPower() + 1);
        }
        this.power = analoguePowerable.getPower();
    }
}
