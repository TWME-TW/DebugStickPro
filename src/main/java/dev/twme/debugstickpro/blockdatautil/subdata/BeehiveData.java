package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Beehive;

public class BeehiveData implements SubBlockData {
    private BlockData blockData;
    private int honeyLevel;
    private boolean isUsing = false;

    public BeehiveData(BlockData blockData) {
        this.blockData = blockData;
        this.honeyLevel = ((Beehive) blockData).getHoneyLevel();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.BeehiveDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(honeyLevel);
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
        Beehive beehive = (Beehive) blockData;
        if (honeyLevel >= beehive.getHoneyLevel()) {
            honeyLevel = 0;
        } else {
            honeyLevel++;
        }
        beehive.setHoneyLevel(honeyLevel);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Beehive) blockData).setHoneyLevel(honeyLevel);
        return blockData;
    }
}