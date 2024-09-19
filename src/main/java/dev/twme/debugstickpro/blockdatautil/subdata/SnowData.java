package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Snow;

public class SnowData extends SubBlockData {
    private int layers;

    public SnowData(BlockData blockData) {
        this.blockData = blockData;
        this.layers = ((Snow) blockData).getLayers();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.SnowDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(layers);
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
