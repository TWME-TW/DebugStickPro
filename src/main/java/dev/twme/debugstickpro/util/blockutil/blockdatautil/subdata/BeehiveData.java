package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Beehive;

public class BeehiveData implements SubBlockData{
    private String NAME = "Beehive";
    private BlockData blockData;
    private int honeyLevel;

    public BeehiveData(BlockData blockData) {
        this.blockData = blockData;
        this.honeyLevel = ((Beehive) blockData).getHoneyLevel();
    }
    @Override
    public String NAME() {
        return NAME;
    }

    @Override
    public BlockData getData() {
        return blockData;
    }

    @Override
    public BlockData getNextData() {
        nextHoneyLevel();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Honey Level: " + honeyLevel;
    }

    @Override
    public String getNextAsString() {
        nextHoneyLevel();
        return "Honey Level: " + honeyLevel;
    }

    @Override
    public String getDataAsString(BlockData blockData) {
        return String.valueOf(honeyLevel);
    }

    @Override
    public String getNextDataAsString() {
        nextHoneyLevel();
        return String.valueOf(honeyLevel);
    }

    private void nextHoneyLevel() {
        Beehive beehive = (Beehive) blockData;
        if (honeyLevel >= beehive.getHoneyLevel()) {
            honeyLevel = 0;
        } else {
            honeyLevel++;
        }
        beehive.setHoneyLevel(honeyLevel);
    }
}
