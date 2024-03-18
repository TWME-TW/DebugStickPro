package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;

public class LevelledData implements SubBlockData {
    private final BlockData blockData;
    private boolean isUsing;
    private int level;

    public LevelledData(BlockData blockData) {
        this.blockData = blockData;
        this.level = ((Levelled) blockData).getLevel();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.LevelledDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(level);
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
    public SubBlockData getDataFac(BlockData blockData) {
        return new LevelledData(blockData);
    }

}
