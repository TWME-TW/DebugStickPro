package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.SculkShrieker;

public class SculkShriekerShriekingData implements SubBlockData{
    private String NAME = "SculkShriekerShrieking";
    private BlockData blockData;
    private boolean isShrieking;
    public SculkShriekerShriekingData(BlockData blockData){
        this.blockData = blockData;
        this.isShrieking = ((SculkShrieker) blockData).isShrieking();
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
        nextData();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Shrieking: " + isShrieking;
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "Shrieking: " + isShrieking;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(isShrieking);
    }

    @Override
    public String getNextDataAsString() {
        nextData();
        return String.valueOf(isShrieking);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData(){
        SculkShrieker sculkShrieker = ((SculkShrieker) blockData);
        isShrieking = !isShrieking;
        sculkShrieker.setShrieking(isShrieking);
    }
}
