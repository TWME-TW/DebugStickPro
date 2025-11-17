package dev.twme.debugstickpro.blockdatautil.subdata;

import java.util.Arrays;
import java.util.List;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.CopperGolemStatue;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;

public class CopperGolemStatuePoseData extends SubBlockData {
    private CopperGolemStatue.Pose pose;

    public CopperGolemStatuePoseData(BlockData blockData) {
        this.blockData = blockData;
        this.pose = ((CopperGolemStatue) blockData).getCopperGolemPose();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.CopperGolemStatuePoseDataName;
    }

    @Override
    public String getDataAsString() {
        return pose.name();
    }

    @Override
    public SubBlockData nextData() {
        CopperGolemStatue copperGolemStatue = ((CopperGolemStatue) blockData);
        List<CopperGolemStatue.Pose> poses = Arrays.stream(CopperGolemStatue.Pose.values()).toList();
        if (poses.indexOf(pose) == poses.size() - 1) {
            pose = poses.getFirst();
        } else {
            pose = poses.get(poses.indexOf(pose) + 1);
        }
        copperGolemStatue.setCopperGolemPose(pose);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        CopperGolemStatue copperGolemStatue = ((CopperGolemStatue) blockData);
        List<CopperGolemStatue.Pose> poses = Arrays.stream(CopperGolemStatue.Pose.values()).toList();
        if (poses.indexOf(pose) == 0) {
            pose = poses.getLast();
        } else {
            pose = poses.get(poses.indexOf(pose) - 1);
        }
        copperGolemStatue.setCopperGolemPose(pose);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((CopperGolemStatue) blockData).setCopperGolemPose(pose);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new CopperGolemStatuePoseData(blockData);
    }
}
