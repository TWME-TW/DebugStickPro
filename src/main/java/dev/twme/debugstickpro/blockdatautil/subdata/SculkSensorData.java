package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.SculkSensor;

public class SculkSensorData extends SubBlockData {
    private SculkSensor.Phase phase;

    public SculkSensorData(BlockData blockData) {
        this.blockData = blockData;
        this.phase = ((SculkSensor) blockData).getPhase();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.SculkSensorDataName;
    }

    @Override
    public String getDataAsString() {
        return phase.name();
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

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new SculkSensorData(blockData);
    }
}
