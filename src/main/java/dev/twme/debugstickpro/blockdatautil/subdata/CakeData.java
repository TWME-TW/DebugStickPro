package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Cake;

public class CakeData extends SubBlockData {
    private int bites;

    public CakeData(BlockData blockData) {
        this.blockData = blockData;
        this.bites = ((Cake) blockData).getBites();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.CakeDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(bites);
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
