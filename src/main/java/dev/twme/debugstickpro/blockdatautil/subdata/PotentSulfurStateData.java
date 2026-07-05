package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.PotentSulfur;

public class PotentSulfurStateData extends SubBlockData {
    private static final PotentSulfur.State[] states = PotentSulfur.State.values();
    private PotentSulfur.State state;

    public PotentSulfurStateData(BlockData blockData) {
        this.blockData = blockData;
        this.state = ((PotentSulfur) blockData).getPotentSulfurState();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.PotentSulfurStateDataName;
    }

    @Override
    public String getDataAsString() {
        return state.name();
    }

    @Override
    public SubBlockData nextData() {
        state = states[(state.ordinal() + 1) % states.length];
        ((PotentSulfur) blockData).setPotentSulfurState(state);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        state = states[(state.ordinal() - 1 + states.length) % states.length];
        ((PotentSulfur) blockData).setPotentSulfurState(state);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((PotentSulfur) blockData).setPotentSulfurState(state);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new PotentSulfurStateData(blockData);
    }
}
