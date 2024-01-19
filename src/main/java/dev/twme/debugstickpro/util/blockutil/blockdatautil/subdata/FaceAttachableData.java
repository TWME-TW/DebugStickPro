package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.FaceAttachable;

public class FaceAttachableData implements SubBlockData{
    private String NAME = "Face Attached";
    private BlockData blockData;
    private FaceAttachable.AttachedFace attachedFace;
    public FaceAttachableData(BlockData blockData) {
        this.blockData = blockData;
        this.attachedFace = ((FaceAttachable) blockData).getAttachedFace();
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
        nextAttachedFace();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Face Attached: " + attachedFace.name();
    }

    @Override
    public String getNextAsString() {
        nextAttachedFace();
        return "Face Attached: " + attachedFace.name();
    }

    @Override
    public String getDataAsString() {
        return attachedFace.name();
    }

    @Override
    public String getNextDataAsString() {
        nextAttachedFace();
        return attachedFace.name();
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextAttachedFace() {
        FaceAttachable faceAttachable = ((FaceAttachable) blockData);
        if (faceAttachable.getAttachedFace() == FaceAttachable.AttachedFace.CEILING) {
            faceAttachable.setAttachedFace(FaceAttachable.AttachedFace.FLOOR);
        } else if (faceAttachable.getAttachedFace() == FaceAttachable.AttachedFace.FLOOR) {
            faceAttachable.setAttachedFace(FaceAttachable.AttachedFace.WALL);
        } else {
            faceAttachable.setAttachedFace(FaceAttachable.AttachedFace.CEILING);
        }
        this.attachedFace = ((FaceAttachable) blockData).getAttachedFace();
    }
}
