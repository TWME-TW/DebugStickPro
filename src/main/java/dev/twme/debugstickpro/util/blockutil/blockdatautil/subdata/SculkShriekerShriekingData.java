package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.SculkShrieker;

public class SculkShriekerShriekingData implements SubBlockData{
    private String NAME = "SculkShriekerShrieking";
    private BlockData blockData;
    private boolean isShrieking;
    private boolean isUsing = false;

    public SculkShriekerShriekingData(BlockData blockData){
        this.blockData = blockData;
        this.isShrieking = ((SculkShrieker) blockData).isShrieking();
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
    public String getAsString() {
        return "Shrieking: " + isShrieking;
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

    public SubBlockData nextData(){
        SculkShrieker sculkShrieker = ((SculkShrieker) blockData);
        isShrieking = !isShrieking;
        sculkShrieker.setShrieking(isShrieking);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((SculkShrieker)blockData).setShrieking(isShrieking);
        return blockData;
    }
}
