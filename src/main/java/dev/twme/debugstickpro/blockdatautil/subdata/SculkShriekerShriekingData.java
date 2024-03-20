package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.SculkShrieker;

public class SculkShriekerShriekingData implements SubBlockData {
    private final BlockData blockData;
    private boolean isShrieking;
    private boolean isUsing = false;

    public SculkShriekerShriekingData(BlockData blockData) {
        this.blockData = blockData;
        this.isShrieking = ((SculkShrieker) blockData).isShrieking();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.SculkShriekerShriekingDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(isShrieking);
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
    public SubBlockData getDataFac(BlockData blockData) {
        return new SculkShriekerShriekingData(blockData);
    }
}
