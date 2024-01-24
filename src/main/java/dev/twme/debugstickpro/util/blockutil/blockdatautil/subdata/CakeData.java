package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Cake;
import org.checkerframework.framework.qual.PreconditionAnnotation;

public class CakeData implements SubBlockData{
    private BlockData blockData;
    private int bites;
    private boolean isUsing = false;

    public CakeData(BlockData blockData){
        this.blockData = blockData;
        this.bites = ((Cake)blockData).getBites();
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
        return LangFile.Cake.replace("%data%", getDataAsString());
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(bites);
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

    @Override
    public SubBlockData nextData(){
        Cake cake = ((Cake) blockData);
        if (cake.getBites() >= (cake.getMaximumBites() - 1)){
            cake.setBites(0);
        } else {
            cake.setBites(cake.getBites() + 1);
        }
        this.bites = cake.getBites();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Cake) blockData).setBites(bites);
        return blockData;
    }
}
