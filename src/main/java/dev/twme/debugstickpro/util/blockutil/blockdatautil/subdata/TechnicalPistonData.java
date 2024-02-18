package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.TechnicalPiston;

public class TechnicalPistonData implements SubBlockData {
    private BlockData blockData;
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
    public BlockData getData() {
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Type: " + type.name();
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
    public BlockData copyTo(BlockData blockData) {
        return null;
    }
}
