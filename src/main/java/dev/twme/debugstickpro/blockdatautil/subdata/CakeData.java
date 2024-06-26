package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Cake;

public class CakeData implements SubBlockData {
    private final BlockData blockData;
    private int bites;
    private boolean isUsing = false;

    public CakeData(BlockData blockData) {
        this.blockData = blockData;
        this.bites = ((Cake) blockData).getBites();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.CakeDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(bites);
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
        Cake cake = ((Cake) blockData);
        if (cake.getBites() >= (cake.getMaximumBites() - 1)) {
            cake.setBites(0);
        } else {
            cake.setBites(cake.getBites() + 1);
        }
        this.bites = cake.getBites();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Cake cake = ((Cake) blockData);
        if (cake.getBites() <= 0) {
            cake.setBites(cake.getMaximumBites());
        } else {
            cake.setBites(cake.getBites() - 1);
        }
        this.bites = cake.getBites();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Cake) blockData).setBites(bites);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new CakeData(blockData);
    }
}
