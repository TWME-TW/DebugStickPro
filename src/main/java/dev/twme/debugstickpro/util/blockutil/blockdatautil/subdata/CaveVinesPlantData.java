package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.CaveVinesPlant;

public class CaveVinesPlantData implements SubBlockData{
    private String NAME = "CaveVinesPlant";
    private BlockData blockData;
    private boolean isBerries;
    public CaveVinesPlantData(BlockData blockData){
        this.blockData = blockData;
        this.isBerries = ((CaveVinesPlant) blockData).isBerries();
    }
    @Override
    public String NAME() {
        return NAME;
    }

    @Override
    public BlockData getData() {
        return blockData;
    }

    @Override
    public BlockData getNextData() {
        nextBerriesProperty();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Berries: " + isBerries;
    }

    @Override
    public String getNextAsString() {
        nextBerriesProperty();
        return "Berries: " + isBerries;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(isBerries);
    }

    @Override
    public String getNextDataAsString() {
        nextBerriesProperty();
        return String.valueOf(isBerries);
    }
    private void nextBerriesProperty(){
        CaveVinesPlant caveVinesPlant = ((CaveVinesPlant) blockData);
        if (caveVinesPlant.isBerries()){
            caveVinesPlant.setBerries(false);
        } else {
            caveVinesPlant.setBerries(true);
        }
        this.isBerries = caveVinesPlant.isBerries();
    }
}
