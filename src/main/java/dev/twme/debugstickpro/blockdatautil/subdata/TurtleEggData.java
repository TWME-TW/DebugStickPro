package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.TurtleEgg;

public class TurtleEggData implements SubBlockData {
    private BlockData blockData;
    private int eggs;
    private boolean isUsing = false;

    public TurtleEggData(BlockData blockData) {
        this.blockData = blockData;
        this.eggs = ((TurtleEgg) blockData).getEggs();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.TurtleEggDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(eggs);
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
    public BlockData copyTo(BlockData blockData) {
        ((TurtleEgg) blockData).setEggs(eggs);
        return blockData;
    }
}
