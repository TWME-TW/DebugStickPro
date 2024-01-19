package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;

public class LevelledData implements SubBlockData{
    private String NAME = "Levelled";
    private BlockData blockData;
    private int level;
    public LevelledData(BlockData blockData){
        this.blockData = blockData;
        this.level = ((Levelled) blockData).getLevel();
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
        nextLevel();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Level: " + level;
    }

    @Override
    public String getNextAsString() {
        nextLevel();
        return "Level: " + level;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(level);
    }

    @Override
    public String getNextDataAsString() {
        nextLevel();
        return String.valueOf(level);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextLevel(){
        Levelled levelled = ((Levelled) blockData);
        if (level == levelled.getMaximumLevel()){
            level = levelled.getMinimumLevel();
        } else {
            level++;
        }
        levelled.setLevel(level);
    }

}
