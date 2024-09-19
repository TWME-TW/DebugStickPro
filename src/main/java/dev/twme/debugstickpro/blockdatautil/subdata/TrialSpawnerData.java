package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.TrialSpawner;

import java.util.List;

public class TrialSpawnerData extends SubBlockData {
    private TrialSpawner.State state;
    private final static List<TrialSpawner.State> states = List.of(
            TrialSpawner.State.ACTIVE,
            TrialSpawner.State.COOLDOWN,
            TrialSpawner.State.EJECTING_REWARD,
            TrialSpawner.State.INACTIVE,
            TrialSpawner.State.WAITING_FOR_PLAYERS,
            TrialSpawner.State.WAITING_FOR_REWARD_EJECTION);

    public TrialSpawnerData(BlockData blockData) {
        this.blockData = blockData;
        this.state = ((TrialSpawner) blockData).getTrialSpawnerState();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.TrialSpawnerDataName;
    }

    @Override
    public String getDataAsString() {
        return state.name();
    }

    public SubBlockData nextData() {
        state = states.get((states.indexOf(state) + 1) % states.size());
        ((TrialSpawner) blockData).setTrialSpawnerState(state);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        state = states.get((states.indexOf(state) - 1 + states.size()) % states.size());
        ((TrialSpawner) blockData).setTrialSpawnerState(state);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((TrialSpawner) blockData).setTrialSpawnerState(state);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new TrialSpawnerData(blockData);
    }
}
