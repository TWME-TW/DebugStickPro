package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.EndPortalFrame;

public class EndPortalFrameData implements SubBlockData{
    private String NAME = "EndPortalFrameData Eye";
    private BlockData blockData;
    private boolean eye;
    private boolean isUsing = false;
    public EndPortalFrameData(BlockData blockData){
        this.blockData = blockData;
        this.eye = ((EndPortalFrame) blockData).hasEye();
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
        return "Eye: " + eye;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(eye);
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
        EndPortalFrame endPortalFrame = ((EndPortalFrame) blockData);
        endPortalFrame.setEye(!endPortalFrame.hasEye());
        this.eye = endPortalFrame.hasEye();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((EndPortalFrame) blockData).setEye(eye);
        return blockData;
    }
}
