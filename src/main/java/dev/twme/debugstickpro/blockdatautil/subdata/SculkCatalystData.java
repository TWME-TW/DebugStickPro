package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.SculkCatalyst;

public class SculkCatalystData extends SubBlockData {
    private boolean isBloom;

    public SculkCatalystData(BlockData blockData) {
        this.blockData = blockData;
        this.isBloom = ((SculkCatalyst) blockData).isBloom();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.SculkCatalystDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(isBloom);
    }

    @Override
    public SubBlockData nextData() {
        SculkCatalyst sculkCatalyst = ((SculkCatalyst) blockData);
        isBloom = !isBloom;
        sculkCatalyst.setBloom(isBloom);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((SculkCatalyst) blockData).setBloom(isBloom);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new SculkCatalystData(blockData);
    }
}
