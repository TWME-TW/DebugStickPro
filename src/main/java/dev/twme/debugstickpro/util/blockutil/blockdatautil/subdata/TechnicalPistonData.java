package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.TechnicalPiston;

public class TechnicalPistonData implements SubBlockData {
    private BlockData blockData;
    private TechnicalPiston.Type type;

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
    public BlockData getNextData() {
        nextData();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Type: " + type.name();
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "Type: " + type.name();
    }

    @Override
    public String getDataAsString() {
        return type.name();
    }

    @Override
    public String getNextDataAsString() {
        nextData();
        return type.name();
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextData() {
        TechnicalPiston technicalPiston = ((TechnicalPiston) blockData);
        if (type == TechnicalPiston.Type.NORMAL) {
            type = TechnicalPiston.Type.STICKY;
        } else if (type == TechnicalPiston.Type.STICKY) {
            type = TechnicalPiston.Type.NORMAL;
        }
        technicalPiston.setType(type);
    }
}
