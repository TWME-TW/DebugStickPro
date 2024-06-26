package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.FaceAttachable;

public class FaceAttachableData implements SubBlockData {
    private final BlockData blockData;
    private FaceAttachable.AttachedFace attachedFace;
    private boolean isUsing = false;

    public FaceAttachableData(BlockData blockData) {
        this.blockData = blockData;
        this.attachedFace = ((FaceAttachable) blockData).getAttachedFace();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.FaceAttachableDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return attachedFace.name();
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
        FaceAttachable faceAttachable = ((FaceAttachable) blockData);
        if (faceAttachable.getAttachedFace() == FaceAttachable.AttachedFace.CEILING) {
            faceAttachable.setAttachedFace(FaceAttachable.AttachedFace.FLOOR);
        } else if (faceAttachable.getAttachedFace() == FaceAttachable.AttachedFace.FLOOR) {
            faceAttachable.setAttachedFace(FaceAttachable.AttachedFace.WALL);
        } else {
            faceAttachable.setAttachedFace(FaceAttachable.AttachedFace.CEILING);
        }
        this.attachedFace = ((FaceAttachable) blockData).getAttachedFace();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        FaceAttachable faceAttachable = ((FaceAttachable) blockData);

        if (faceAttachable.getAttachedFace() == FaceAttachable.AttachedFace.WALL) {
            faceAttachable.setAttachedFace(FaceAttachable.AttachedFace.FLOOR);
        } else if (faceAttachable.getAttachedFace() == FaceAttachable.AttachedFace.FLOOR) {
            faceAttachable.setAttachedFace(FaceAttachable.AttachedFace.CEILING);
        } else {
            faceAttachable.setAttachedFace(FaceAttachable.AttachedFace.WALL);
        }
        this.attachedFace = ((FaceAttachable) blockData).getAttachedFace();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((FaceAttachable) blockData).setAttachedFace(attachedFace);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new FaceAttachableData(blockData);
    }
}
