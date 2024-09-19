package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.EndPortalFrame;

public class EndPortalFrameData extends SubBlockData {
    private boolean eye;

    public EndPortalFrameData(BlockData blockData) {
        this.blockData = blockData;
        this.eye = ((EndPortalFrame) blockData).hasEye();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.EndPortalFrameDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(eye);
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
    public SubBlockData fromBlockData(BlockData blockData) {
        return new EndPortalFrameData(blockData);
    }
}
