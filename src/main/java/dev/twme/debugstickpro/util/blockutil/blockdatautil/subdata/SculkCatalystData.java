package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.SculkCatalyst;

public class SculkCatalystData implements SubBlockData{
    private String NAME = "SculkCatalyst";
    private BlockData blockData;
    private boolean isBloom;
    public SculkCatalystData(BlockData blockData){
        this.blockData = blockData;
        this.isBloom = ((SculkCatalyst) blockData).isBloom();
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
        nextData();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Bloom: " + isBloom;
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "Bloom: " + isBloom;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(isBloom);
    }

    @Override
    public String getNextDataAsString() {
        nextData();
        return String.valueOf(isBloom);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData(){
        SculkCatalyst sculkCatalyst = ((SculkCatalyst) blockData);
        isBloom = !isBloom;
        sculkCatalyst.setBloom(isBloom);
    }
}
