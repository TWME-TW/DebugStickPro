package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.SculkCatalyst;

public class SculkCatalystData implements SubBlockData{
    private String NAME = "SculkCatalyst";
    private BlockData blockData;
    private boolean isBloom;
    private boolean isUsing = false;
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
    public String getAsString() {
        return "Bloom: " + isBloom;
    }



    @Override
    public String getDataAsString() {
        return String.valueOf(isBloom);
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
        SculkCatalyst sculkCatalyst = ((SculkCatalyst) blockData);
        isBloom = !isBloom;
        sculkCatalyst.setBloom(isBloom);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((SculkCatalyst)blockData).setBloom(isBloom);
        return blockData;
    }
}
