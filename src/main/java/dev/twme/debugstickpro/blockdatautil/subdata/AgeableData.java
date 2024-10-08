package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;

public class AgeableData extends SubBlockData {
    private int age;

    public AgeableData(BlockData blockData) {
        this.blockData = blockData;
        this.age = ((Ageable) blockData).getAge();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.AgeableDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(age);
    }

    @Override
    public SubBlockData nextData() {
        Ageable age = (Ageable) blockData;
        if (age.getAge() >= age.getMaximumAge()) {
            age.setAge(0);
        } else {
            age.setAge(age.getAge() + 1);
        }
        this.age = age.getAge();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Ageable age = (Ageable) blockData;
        if (age.getAge() <= 0) {
            age.setAge(age.getMaximumAge());
        } else {
            age.setAge(age.getAge() - 1);
        }
        this.age = age.getAge();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Ageable) blockData).setAge(this.age);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new AgeableData(blockData);
    }
}
