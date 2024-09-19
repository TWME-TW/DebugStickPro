package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Gate;

public class GateData extends SubBlockData {
    private boolean inWall;

    public GateData(BlockData blockData) {
        this.blockData = blockData;
        this.inWall = ((Gate) blockData).isInWall();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.GateDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(inWall);
    }

    @Override
    public SubBlockData nextData() {
        Gate gate = ((org.bukkit.block.data.type.Gate) blockData);
        gate.setInWall(!gate.isInWall());
        this.inWall = gate.isInWall();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Gate) blockData).setInWall(inWall);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new GateData(blockData);
    }
}
