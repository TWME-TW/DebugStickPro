package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Chest;

public class ChestData implements SubBlockData {
    private BlockData blockData;
    private Chest.Type type;
    private boolean isUsing = false;

    public ChestData(BlockData blockData) {
        this.blockData = blockData;
        this.type = ((Chest) blockData).getType();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.ChestDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return type.name();
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
        Chest chest = (Chest) blockData;
        if (chest.getType() == Chest.Type.SINGLE) {
            chest.setType(Chest.Type.LEFT);
        } else if (chest.getType() == Chest.Type.LEFT) {
            chest.setType(Chest.Type.RIGHT);
        } else {
            chest.setType(Chest.Type.SINGLE);
        }
        this.type = chest.getType();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Chest) blockData).setType(type);
        return blockData;
    }
}
