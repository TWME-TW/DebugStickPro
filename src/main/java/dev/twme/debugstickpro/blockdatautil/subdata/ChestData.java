package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Chest;

public class ChestData extends SubBlockData {
    private Chest.Type type;

    public ChestData(BlockData blockData) {
        this.blockData = blockData;
        this.type = ((Chest) blockData).getType();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.ChestDataName;
    }

    @Override
    public String getDataAsString() {
        return type.name();
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
    public SubBlockData previousData() {
        Chest chest = (Chest) blockData;
        if (chest.getType() == Chest.Type.SINGLE) {
            chest.setType(Chest.Type.RIGHT);
        } else if (chest.getType() == Chest.Type.RIGHT) {
            chest.setType(Chest.Type.LEFT);
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

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new ChestData(blockData);
    }
}
