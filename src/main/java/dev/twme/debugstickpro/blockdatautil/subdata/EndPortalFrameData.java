package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.EndPortalFrame;

public class EndPortalFrameData implements SubBlockData {
    private final BlockData blockData;
    private boolean eye;
    private boolean isUsing = false;

    public EndPortalFrameData(BlockData blockData) {
        this.blockData = blockData;
        this.eye = ((EndPortalFrame) blockData).hasEye();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.EndPortalFrameDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
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
    public SubBlockData nextData() {
        EndPortalFrame endPortalFrame = ((EndPortalFrame) blockData);
        endPortalFrame.setEye(!endPortalFrame.hasEye());
        this.eye = endPortalFrame.hasEye();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((EndPortalFrame) blockData).setEye(eye);
        return blockData;
    }

    @Override
    public SubBlockData getDataFac(BlockData blockData) {
        return new EndPortalFrameData(blockData);
    }
}
