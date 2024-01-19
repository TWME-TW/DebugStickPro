package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;

public class AgeableData implements SubBlockData {
    private final String NAME = "Age";
    private BlockData blockData;
    private int age;

    public AgeableData(BlockData blockData) {
        this.blockData = blockData;
        this.age = ((Ageable) blockData).getAge();
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
        nextAge();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Age: " + age;
    }

    @Override
    public String getNextAsString() {
        nextAge();
        return "Age: " + age;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(age);
    }

    @Override
    public String getNextDataAsString() {
        nextAge();
        return String.valueOf(age);
    }

    private void nextAge() {
        Ageable age = (Ageable) blockData;
        if (age.getAge() >= age.getMaximumAge()) {
            age.setAge(0);
        } else {
            age.setAge(age.getAge() + 1);
        }
        this.age = age.getAge();
    }
}
