package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.SculkShrieker;

public class SculkShriekerShriekingData extends SubBlockData {
    private boolean isShrieking;

    public SculkShriekerShriekingData(BlockData blockData) {
        this.blockData = blockData;
        this.isShrieking = ((SculkShrieker) blockData).isShrieking();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.SculkShriekerShriekingDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(isShrieking);
    }

    public SubBlockData nextData() {
        SculkShrieker sculkShrieker = ((SculkShrieker) blockData);
        isShrieking = !isShrieking;
        sculkShrieker.setShrieking(isShrieking);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((SculkShrieker) blockData).setShrieking(isShrieking);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new SculkShriekerShriekingData(blockData);
    }
}
