package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Vault;

public class VaultStateData extends SubBlockData {
    private Vault.State state;

    public VaultStateData(BlockData blockData) {
        this.blockData = blockData;
        this.state = ((Vault) blockData).getVaultState();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.VaultStateDataName;
    }

    @Override
    public String getDataAsString() {
        return state.name();
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
        ((Vault) blockData).setVaultState(state);
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
        ((Vault) blockData).setVaultState(state);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Vault) blockData).setVaultState(state);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new VaultStateData(blockData);
    }
}
