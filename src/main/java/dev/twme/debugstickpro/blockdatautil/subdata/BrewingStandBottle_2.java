package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;

public class BrewingStandBottle_2 extends BrewingStandBottle {

    public BrewingStandBottle_2(BlockData blockData) {
        super(blockData, 2);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.BrewingStandBottle_2DataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new BrewingStandBottle_2(blockData);
    }
}