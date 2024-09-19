package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.TurtleEgg;

public class TurtleEggData extends SubBlockData {
    private int eggs;

    public TurtleEggData(BlockData blockData) {
        this.blockData = blockData;
        this.eggs = ((TurtleEgg) blockData).getEggs();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.TurtleEggDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(eggs);
    }

    public SubBlockData nextData() {
        TurtleEgg turtleEgg = ((TurtleEgg) blockData);
        if (eggs >= turtleEgg.getMaximumEggs()) {
            eggs = turtleEgg.getMinimumEggs();
        } else {
            eggs++;
        }
        turtleEgg.setEggs(eggs);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        TurtleEgg turtleEgg = ((TurtleEgg) blockData);
        if (eggs <= turtleEgg.getMinimumEggs()) {
            eggs = turtleEgg.getMaximumEggs();
        } else {
            eggs--;
        }
        turtleEgg.setEggs(eggs);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((TurtleEgg) blockData).setEggs(eggs);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new TurtleEggData(blockData);
    }
}
