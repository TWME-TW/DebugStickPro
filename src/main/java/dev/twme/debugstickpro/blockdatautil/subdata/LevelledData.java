package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;

public class LevelledData extends SubBlockData {
    private int level;

    public LevelledData(BlockData blockData) {
        this.blockData = blockData;
        this.level = ((Levelled) blockData).getLevel();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.LevelledDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(level);
    }

    public SubBlockData nextData() {
        Levelled levelled = ((Levelled) blockData);
        if (level == levelled.getMaximumLevel()) {
            level = levelled.getMinimumLevel();
        } else {
            level++;
        }
        levelled.setLevel(level);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Levelled levelled = ((Levelled) blockData);
        if (level == levelled.getMinimumLevel()) {
            level = levelled.getMaximumLevel();
        } else {
            level--;
        }
        levelled.setLevel(level);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Levelled) blockData).setLevel(level);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new LevelledData(blockData);
    }

}
