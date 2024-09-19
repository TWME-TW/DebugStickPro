package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.FaceAttachable;

import java.util.List;

public class FaceAttachableData extends SubBlockData {
    private FaceAttachable.AttachedFace attachedFace;
    private final static List<FaceAttachable.AttachedFace> faces = List.of(
            FaceAttachable.AttachedFace.CEILING,
            FaceAttachable.AttachedFace.FLOOR,
            FaceAttachable.AttachedFace.WALL);

    public FaceAttachableData(BlockData blockData) {
        this.blockData = blockData;
        this.attachedFace = ((FaceAttachable) blockData).getAttachedFace();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.FaceAttachableDataName;
    }

    @Override
    public String getDataAsString() {
        return attachedFace.name();
    }

    @Override
    public SubBlockData nextData() {
        FaceAttachable.AttachedFace attachedFace = ((FaceAttachable) blockData).getAttachedFace();
        this.attachedFace = faces.get((faces.indexOf(attachedFace) + 1) % faces.size());
        return this;
    }

    @Override
    public SubBlockData previousData() {
        FaceAttachable.AttachedFace attachedFace = ((FaceAttachable) blockData).getAttachedFace();
        this.attachedFace = faces.get((faces.indexOf(attachedFace) - 1 + faces.size()) % faces.size());
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
