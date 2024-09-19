package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.TechnicalPiston;

public class TechnicalPistonData extends SubBlockData {
    private TechnicalPiston.Type type;

    public TechnicalPistonData(BlockData blockData) {
        this.blockData = blockData;
        this.type = ((TechnicalPiston) blockData).getType();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.TechnicalPistonDataName;
    }

    @Override
    public String getDataAsString() {
        return type.name();
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
    public SubBlockData fromBlockData(BlockData blockData) {
        return new TechnicalPistonData(blockData);
    }
}
