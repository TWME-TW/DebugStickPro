package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;

public class AgeableData implements SubBlockData {

    public final String NAME = "Age";
    public BlockData blockData;

    public AgeableData(BlockData blockData) {
        this.blockData = blockData;
    }

    private int age;

    @Override
    public String NAME() {
        return NAME;
    }

    @Override
    public String getData() {
        Ageable age = (Ageable) blockData;
        this.age = age.getAge();
        return String.valueOf(this.age);
    }
    @Override
    public BlockData setNextData() {
        Ageable age = (Ageable) blockData;
        if (age.getAge() >= age.getMaximumAge()) {
            age.setAge(0);
        } else {
            age.setAge(age.getAge() + 1);
        }
        return blockData;
    }

    @Override
    public String getAsString() {
        String str = "Age: " + age;
        return null;
    }
}
