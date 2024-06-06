package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Vault;

public class VaultStateData implements SubBlockData {
    private final BlockData blockData;
    private boolean isUsing = false;
    private Vault.State state;

    public VaultStateData(BlockData blockData) {
        this.blockData = blockData;
        this.state = ((Vault) blockData).getTrialSpawnerState();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.VaultStateDataName;
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

    @Override
    public SubBlockData nextData() {
        if (state == Vault.State.ACTIVE) {
            state = Vault.State.EJECTING;
        } else if (state == Vault.State.EJECTING) {
            state = Vault.State.INACTIVE;
        } else if (state == Vault.State.INACTIVE) {
            state = Vault.State.UNLOCKING;
        } else if (state == Vault.State.UNLOCKING) {
            state = Vault.State.ACTIVE;
        }
        ((Vault) blockData).setTrialSpawnerState(state);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        if (state == Vault.State.ACTIVE) {
            state = Vault.State.UNLOCKING;
        } else if (state == Vault.State.UNLOCKING) {
            state = Vault.State.INACTIVE;
        } else if (state == Vault.State.INACTIVE) {
            state = Vault.State.EJECTING;
        } else if (state == Vault.State.EJECTING) {
            state = Vault.State.ACTIVE;
        }
        ((Vault) blockData).setTrialSpawnerState(state);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Vault) blockData).setTrialSpawnerState(state);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new VaultStateData(blockData);
    }
}
