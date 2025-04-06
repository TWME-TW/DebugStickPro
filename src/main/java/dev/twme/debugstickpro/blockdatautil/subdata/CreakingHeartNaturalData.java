package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.CreakingHeart;

public class CreakingHeartNaturalData extends SubBlockData {
    private boolean isNatural;

    public CreakingHeartNaturalData(BlockData blockData) {
        this.blockData = blockData;
        this.isNatural = ((CreakingHeart) blockData).isNatural();

    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.CreakingHeartNaturalDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(isNatural);
    }

    @Override
    public SubBlockData nextData() {
        this.isNatural = !this.isNatural;
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((CreakingHeart) blockData).setNatural(this.isNatural);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new CreakingHeartNaturalData(blockData);
    }
}
