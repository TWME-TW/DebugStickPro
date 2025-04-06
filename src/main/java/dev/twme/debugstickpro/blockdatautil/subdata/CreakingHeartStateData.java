package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.CreakingHeart;

public class CreakingHeartStateData extends SubBlockData {
    private boolean isActive;

    public CreakingHeartStateData(BlockData blockData) {
        this.blockData = blockData;
        this.isActive = ((CreakingHeart) blockData).isActive();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.CreakingHeartStateDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(isActive);
    }

    @Override
    public SubBlockData nextData() {
        this.isActive = !isActive;
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((CreakingHeartStateData) blockData).isActive = this.isActive;
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new CreakingHeartStateData(blockData);
    }
}
