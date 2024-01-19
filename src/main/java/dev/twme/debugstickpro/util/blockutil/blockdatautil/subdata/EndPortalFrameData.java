package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.EndPortalFrame;

public class EndPortalFrameData implements SubBlockData{
    private String NAME = "EndPortalFrameData Eye";
    private BlockData blockData;
    private boolean eye;
    public EndPortalFrameData(BlockData blockData){
        this.blockData = blockData;
        this.eye = ((EndPortalFrame) blockData).hasEye();
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
        nextEyeProperty();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Eye: " + eye;
    }

    @Override
    public String getNextAsString() {
        nextEyeProperty();
        return "Eye: " + eye;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(eye);
    }

    @Override
    public String getNextDataAsString() {
        nextEyeProperty();
        return String.valueOf(eye);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextEyeProperty(){
        EndPortalFrame endPortalFrame = ((EndPortalFrame) blockData);
        endPortalFrame.setEye(!endPortalFrame.hasEye());
        this.eye = endPortalFrame.hasEye();
    }
}
