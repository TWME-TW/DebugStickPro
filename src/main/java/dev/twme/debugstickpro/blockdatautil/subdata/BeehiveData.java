package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Beehive;

public class BeehiveData extends SubBlockData {
    private int honeyLevel;

    public BeehiveData(BlockData blockData) {
        this.blockData = blockData;
        this.honeyLevel = ((Beehive) blockData).getHoneyLevel();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.BeehiveDataName;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(honeyLevel);
    }


    @Override
    public SubBlockData nextData() {
        Beehive beehive = (Beehive) blockData;
        if (honeyLevel >= beehive.getMaximumHoneyLevel()) {
            honeyLevel = 0;
        } else {
            honeyLevel++;
        }
        beehive.setHoneyLevel(honeyLevel);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Beehive beehive = (Beehive) blockData;
        if (honeyLevel <= 0) {
            honeyLevel = beehive.getMaximumHoneyLevel();
        } else {
            honeyLevel--;
        }
        beehive.setHoneyLevel(honeyLevel);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Beehive) blockData).setHoneyLevel(honeyLevel);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new BeehiveData(blockData);
    }
}
