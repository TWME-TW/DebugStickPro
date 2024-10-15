package dev.twme.debugstickpro.blockdatautil.subdata.brewingstandbottle;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;

public class BrewingStandBottle_0 extends BrewingStandBottle {

    public BrewingStandBottle_0(BlockData blockData) {
        super(blockData, 0);
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.BrewingStandBottle_0DataName;
    }


    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new BrewingStandBottle_0(blockData);
    }
}