package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.SculkSensor;

public class SculkSensorData implements SubBlockData{
    private String NAME = "SculkSensor";
    private BlockData blockData;
    private SculkSensor.Phase phase;
    private boolean isUsing = false;
    public SculkSensorData(BlockData blockData){
        this.blockData = blockData;
        this.phase = ((SculkSensor) blockData).getPhase();
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }



    @Override
    public String getAsString() {
        return "Phase: " + phase;
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

    public SubBlockData nextData(){
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
    public BlockData copyTo(BlockData blockData) {
        ((SculkSensor)blockData).setPhase(phase);
        return blockData;
    }
}
