package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Gate;

public class GateData implements SubBlockData {

    private BlockData blockData;
    private boolean inWall;
    private boolean isUsing = false;

    public GateData(BlockData blockData) {
        this.blockData = blockData;
        this.inWall = ((Gate) blockData).isInWall();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.GateDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(inWall);
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
        Gate gate = ((org.bukkit.block.data.type.Gate) blockData);
        gate.setInWall(!gate.isInWall());
        this.inWall = gate.isInWall();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Gate) blockData).setInWall(inWall);
        return blockData;
    }
}
