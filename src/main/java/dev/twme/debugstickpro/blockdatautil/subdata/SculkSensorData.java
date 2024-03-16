package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.SculkSensor;

public class SculkSensorData implements SubBlockData {
    private BlockData blockData;
    private SculkSensor.Phase phase;
    private boolean isUsing = false;

    public SculkSensorData(BlockData blockData) {
        this.blockData = blockData;
        this.phase = ((SculkSensor) blockData).getPhase();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.SculkSensorDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return phase.name();
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

    public SubBlockData nextData() {
        SculkSensor sculkSensor = ((SculkSensor) blockData);
        if (phase == SculkSensor.Phase.ACTIVE) {
            phase = SculkSensor.Phase.COOLDOWN;
        } else if (phase == SculkSensor.Phase.COOLDOWN) {
            phase = SculkSensor.Phase.INACTIVE;
        } else {
            phase = SculkSensor.Phase.ACTIVE;
        }
        sculkSensor.setPhase(phase);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        SculkSensor sculkSensor = ((SculkSensor) blockData);
        if (phase == SculkSensor.Phase.ACTIVE) {
            phase = SculkSensor.Phase.INACTIVE;
        } else if (phase == SculkSensor.Phase.COOLDOWN) {
            phase = SculkSensor.Phase.ACTIVE;
        } else {
            phase = SculkSensor.Phase.COOLDOWN;
        }
        sculkSensor.setPhase(phase);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((SculkSensor) blockData).setPhase(phase);
        return blockData;
    }
}
