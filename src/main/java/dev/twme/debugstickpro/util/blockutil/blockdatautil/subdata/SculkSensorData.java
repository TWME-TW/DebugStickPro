package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.SculkSensor;

public class SculkSensorData implements SubBlockData{
    private String NAME = "SculkSensor";
    private BlockData blockData;
    private SculkSensor.Phase phase;
    public SculkSensorData(BlockData blockData){
        this.blockData = blockData;
        this.phase = ((SculkSensor) blockData).getPhase();
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public BlockData getData() {
        return blockData;
    }

    @Override
    public BlockData getNextData() {
        nextData();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Phase: " + phase;
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "Phase: " + phase;
    }

    @Override
    public String getDataAsString() {
        return phase.name();
    }

    @Override
    public String getNextDataAsString() {
        nextData();
        return phase.name();
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData(){
        SculkSensor sculkSensor = ((SculkSensor) blockData);
        if (phase == SculkSensor.Phase.ACTIVE) {
            phase = SculkSensor.Phase.COOLDOWN;
        } else if (phase == SculkSensor.Phase.COOLDOWN) {
            phase = SculkSensor.Phase.INACTIVE;
        } else {
            phase = SculkSensor.Phase.ACTIVE;
        }
        sculkSensor.setPhase(phase);
    }
}
