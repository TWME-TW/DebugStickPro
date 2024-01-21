package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;

public class AgeableData implements SubBlockData {
    private BlockData blockData;
    private int age;
    private boolean isUsing = false;

    public AgeableData(BlockData blockData) {
        this.blockData = blockData;
        this.age = ((Ageable) blockData).getAge();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getDisplayName() {
        return LangFile.ActionBar.formatSelectedData(getAsString(),isUsing);
    }

    @Override
    public BlockData getData() {
        return blockData;
    }


    @Override
    public String getAsString() {
        return LangFile.Ageable.replace("%age%",String.valueOf(age));
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(age);
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
    public BlockData copyTo(BlockData blockData) {
        ((Ageable) blockData).setAge(this.age);
        return blockData;
    }
}
