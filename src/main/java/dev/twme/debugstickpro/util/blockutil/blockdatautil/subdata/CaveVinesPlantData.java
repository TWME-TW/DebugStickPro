package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.CaveVinesPlant;

public class CaveVinesPlantData implements SubBlockData{
    private BlockData blockData;
    private boolean isBerries;
    private boolean isUsing = false;
    public CaveVinesPlantData(BlockData blockData){
        this.blockData = blockData;
        this.isBerries = ((CaveVinesPlant) blockData).isBerries();
    }
    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public BlockData getData() {
        return blockData;
    }


    @Override
    public String getAsString() {
        return LangFile.CaveVinesPlant.replace("%data%",getDataAsString());
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(isBerries);
    }


    @Override
    public void setIsUsing(boolean isUsing) {
        this.isUsing = isUsing;
    }

    @Override
    public boolean isUsing() {
        return isUsing;
    }

    @Override
    public SubBlockData nextData(){
        CaveVinesPlant caveVinesPlant = ((CaveVinesPlant) blockData);
        caveVinesPlant.setBerries(!caveVinesPlant.isBerries());
        this.isBerries = caveVinesPlant.isBerries();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((CaveVinesPlant)blockData).setBerries(isBerries);
        return blockData;
    }
}
