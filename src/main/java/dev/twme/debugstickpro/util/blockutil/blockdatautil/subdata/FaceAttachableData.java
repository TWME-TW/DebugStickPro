package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.FaceAttachable;

public class FaceAttachableData implements SubBlockData {
    private String NAME = "Face Attached";
    private BlockData blockData;
    private FaceAttachable.AttachedFace attachedFace;
    private boolean isUsing = false;

    public FaceAttachableData(BlockData blockData) {
        this.blockData = blockData;
        this.attachedFace = ((FaceAttachable) blockData).getAttachedFace();
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String dataName() {
        return LangFile.FaceAttachableDataName;
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
    public BlockData copyTo(BlockData blockData) {
        ((FaceAttachable) blockData).setAttachedFace(attachedFace);
        return blockData;
    }
}
