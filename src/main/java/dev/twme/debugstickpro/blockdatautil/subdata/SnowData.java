package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Snow;

public class SnowData implements SubBlockData {
    private final BlockData blockData;
    private int layers;
    private boolean isUsing = false;

    public SnowData(BlockData blockData) {
        this.blockData = blockData;
        this.layers = ((Snow) blockData).getLayers();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.SnowDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
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

    public SubBlockData nextData() {
        Snow snow = ((Snow) blockData);
        if (layers >= snow.getMaximumLayers()) {
            layers = snow.getMinimumLayers();
        } else {
            layers++;
        }
        snow.setLayers(layers);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Snow snow = ((Snow) blockData);
        if (layers <= snow.getMinimumLayers()) {
            layers = snow.getMaximumLayers();
        } else {
            layers--;
        }
        snow.setLayers(layers);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Snow) blockData).setLayers(layers);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new SnowData(blockData);
    }
}
