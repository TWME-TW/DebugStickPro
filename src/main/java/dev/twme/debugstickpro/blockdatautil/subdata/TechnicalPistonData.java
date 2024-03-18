package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.TechnicalPiston;

public class TechnicalPistonData implements SubBlockData {
    private final BlockData blockData;
    private TechnicalPiston.Type type;
    private boolean isUsing = false;

    public TechnicalPistonData(BlockData blockData) {
        this.blockData = blockData;
        this.type = ((TechnicalPiston) blockData).getType();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.TechnicalPistonDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return type.name();
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
        TechnicalPiston technicalPiston = ((TechnicalPiston) blockData);
        if (type == TechnicalPiston.Type.NORMAL) {
            type = TechnicalPiston.Type.STICKY;
        } else if (type == TechnicalPiston.Type.STICKY) {
            type = TechnicalPiston.Type.NORMAL;
        }
        technicalPiston.setType(type);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((TechnicalPiston) blockData).setType(type);
        return blockData;
    }

    @Override
    public SubBlockData getDataFac(BlockData blockData) {
        return new TechnicalPistonData(blockData);
    }
}
