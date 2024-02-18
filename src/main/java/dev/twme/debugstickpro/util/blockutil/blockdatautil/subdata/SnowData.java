package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Snow;

public class SnowData implements SubBlockData{
    private String NAME = "Snow";
    private BlockData blockData;
    private int layers;
    private boolean isUsing = false;
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
    public String getAsString() {
        return "Layers: " + layers;
    }



    @Override
    public String getDataAsString() {
        return String.valueOf(layers);
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

    public SubBlockData nextData(){
        Snow snow = ((Snow) blockData);
        if (layers >= snow.getMaximumLayers()){
            layers = snow.getMinimumLayers();
        } else {
            layers++;
        }
        snow.setLayers(layers);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Snow)blockData).setLayers(layers);
        return blockData;
    }
}
