package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Snow;

public class SnowData implements SubBlockData{
    private String NAME = "Snow";
    private BlockData blockData;
    private int layers;
    public SnowData(BlockData blockData){
        this.blockData = blockData;
        this.layers = ((Snow) blockData).getLayers();
    }
    @Override
    public String name() {
        return NAME;
    }

    @Override
    public BlockData getData() {
        return blockData;
    }

    @Override
    public BlockData getNextData() {
        nextData();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Layers: " + layers;
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "Layers: " + layers;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(layers);
    }

    @Override
    public String getNextDataAsString() {
        nextData();
        return String.valueOf(layers);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData(){
        Snow snow = ((Snow) blockData);
        if (layers >= snow.getMaximumLayers()){
            layers = snow.getMinimumLayers();
        } else {
            layers++;
        }
        snow.setLayers(layers);
    }
}
