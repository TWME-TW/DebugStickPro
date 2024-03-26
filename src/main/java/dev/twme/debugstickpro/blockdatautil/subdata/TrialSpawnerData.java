package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.TrialSpawner;

public class TrialSpawnerData implements SubBlockData {
    private final BlockData blockData;
    private TrialSpawner.State state;
    private boolean isUsing = false;

    public TrialSpawnerData(BlockData blockData) {
        this.blockData = blockData;
        this.state = ((TrialSpawner) blockData).getTrialSpawnerState();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.TrialSpawnerDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return state.name();
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
        return this;
    }

    @Override
    public SubBlockData previousData() {
        TrialSpawner trialSpawner = ((TrialSpawner) blockData);

        if (state == TrialSpawner.State.WAITING_FOR_REWARD_EJECTION) {
            state = TrialSpawner.State.WAITING_FOR_PLAYERS;
        } else if (state == TrialSpawner.State.WAITING_FOR_PLAYERS) {
            state = TrialSpawner.State.INACTIVE;
        } else if (state == TrialSpawner.State.INACTIVE) {
            state = TrialSpawner.State.EJECTING_REWARD;
        } else if (state == TrialSpawner.State.EJECTING_REWARD) {
            state = TrialSpawner.State.COOLDOWN;
        } else if (state == TrialSpawner.State.COOLDOWN) {
            state = TrialSpawner.State.ACTIVE;
        } else {
            state = TrialSpawner.State.WAITING_FOR_REWARD_EJECTION;
        }
        trialSpawner.setTrialSpawnerState(state);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((TrialSpawner) blockData).setTrialSpawnerState(state);
        return blockData;
    }

    @Override
    public SubBlockData getDataFac(BlockData blockData) {
        return new TrialSpawnerData(blockData);
    }
}
