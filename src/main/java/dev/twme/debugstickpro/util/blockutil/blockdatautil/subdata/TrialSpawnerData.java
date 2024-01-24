package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.TrialSpawner;

public class TrialSpawnerData implements SubBlockData{
    private BlockData blockData;
    private TrialSpawner.State state;
    public TrialSpawnerData(BlockData blockData){
        this.blockData = blockData;
        this.state = ((TrialSpawner) blockData).getTrialSpawnerState();
    }
    @Override
    public String name() {
        return this.getClass().getSimpleName();
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
        return "State: " + state.name();
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "State: " + state.name();
    }

    @Override
    public String getDataAsString() {
        return state.name();
    }

    @Override
    public String getNextDataAsString() {
        nextData();
        return state.name();
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData(){
        TrialSpawner trialSpawner = ((TrialSpawner) blockData);
        if (state == TrialSpawner.State.ACTIVE) {
            state = TrialSpawner.State.COOLDOWN;
        } else if (state == TrialSpawner.State.COOLDOWN) {
            state = TrialSpawner.State.EJECTING_REWARD;
        } else if (state == TrialSpawner.State.EJECTING_REWARD) {
            state = TrialSpawner.State.INACTIVE;
        } else if (state == TrialSpawner.State.INACTIVE) {
            state = TrialSpawner.State.WAITING_FOR_PLAYERS;
        } else if (state == TrialSpawner.State.WAITING_FOR_PLAYERS) {
            state = TrialSpawner.State.WAITING_FOR_REWARD_EJECTION;
        } else {
            state = TrialSpawner.State.ACTIVE;
        }
        trialSpawner.setTrialSpawnerState(state);
    }
}