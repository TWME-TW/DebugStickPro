package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.CaveVinesPlant;

public class CaveVinesPlantData extends SubBlockData {
    private boolean isBerries;

    public CaveVinesPlantData(BlockData blockData) {
        this.blockData = blockData;
        this.isBerries = ((CaveVinesPlant) blockData).isBerries();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.CaveVinesPlantDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(isBerries);
    }

    @Override
    public SubBlockData nextData() {
        CaveVinesPlant caveVinesPlant = ((CaveVinesPlant) blockData);
        caveVinesPlant.setBerries(!caveVinesPlant.isBerries());
        this.isBerries = caveVinesPlant.isBerries();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((CaveVinesPlant) blockData).setBerries(isBerries);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new CaveVinesPlantData(blockData);
    }
}
