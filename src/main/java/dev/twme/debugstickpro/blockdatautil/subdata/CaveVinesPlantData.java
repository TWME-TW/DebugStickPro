package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.CaveVinesPlant;

public class CaveVinesPlantData implements SubBlockData {
    private final BlockData blockData;
    private boolean isBerries;
    private boolean isUsing = false;

    public CaveVinesPlantData(BlockData blockData) {
        this.blockData = blockData;
        this.isBerries = ((CaveVinesPlant) blockData).isBerries();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.CaveVinesPlantDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(isBerries);
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
    public SubBlockData getDataFac(BlockData blockData) {
        return new CaveVinesPlantData(blockData);
    }
}
