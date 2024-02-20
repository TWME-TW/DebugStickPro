package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;

public class LevelledData implements SubBlockData{
    private String NAME = "Levelled";
    private BlockData blockData;
    private boolean isUsing;
    private int level;
    public LevelledData(BlockData blockData){
        this.blockData = blockData;
        this.level = ((Levelled) blockData).getLevel();
    }
    @Override
    public String name() {
        return NAME;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }



    @Override
    public String getAsString() {
        return "Level: " + level;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(level);
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

    public SubBlockData nextData(){
        Levelled levelled = ((Levelled) blockData);
        if (level == levelled.getMaximumLevel()){
            level = levelled.getMinimumLevel();
        } else {
            level++;
        }
        levelled.setLevel(level);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Levelled) blockData).setLevel(level);
        return blockData;
    }

}
