package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Chest;

public class ChestData implements SubBlockData {
    private final String NAME = "Chest Type";
    private BlockData blockData;
    private Chest.Type type;

    public ChestData(BlockData blockData) {
        this.blockData = blockData;
        this.type = ((Chest) blockData).getType();
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public BlockData getData() {
        return blockData;
    }

    @Override
    public BlockData getNextData() {
        nextType();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Chest Type: " + type.name();
    }

    @Override
    public String getNextAsString() {
        nextType();
        return "Chest Type: " + type.name();
    }

    @Override
    public String getDataAsString() {
        return type.name();
    }

    @Override
    public String getNextDataAsString() {
        nextType();
        return type.name();
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextType() {
        Chest chest = (Chest) blockData;
        if (chest.getType() == Chest.Type.SINGLE) {
            chest.setType(Chest.Type.LEFT);
        } else if (chest.getType() == Chest.Type.LEFT) {
            chest.setType(Chest.Type.RIGHT);
        } else {
            chest.setType(Chest.Type.SINGLE);
        }
        this.type = chest.getType();
    }
}
