package dev.twme.debugstickpro.blockdatautil.subdata.brewingstandbottle;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;

public class BrewingStandBottle_1 extends BrewingStandBottle {

    public BrewingStandBottle_1(BlockData blockData) {
        super(blockData, 1);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.BrewingStandBottle_1DataName;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new BrewingStandBottle_1(blockData);
    }
}