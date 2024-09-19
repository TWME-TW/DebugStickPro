package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Vault;

public class VaultOminousData extends SubBlockData {
    private boolean ominous;

    public VaultOminousData(BlockData blockData) {
        this.blockData = blockData;
        this.ominous = ((Vault) blockData).isOminous();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.VaultOminousDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(ominous);
    }

    @Override
    public SubBlockData nextData() {
        Vault vault = ((Vault) blockData);
        vault.setOminous(!ominous);
        ominous = vault.isOminous();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Vault) blockData).setOminous(ominous);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new VaultOminousData(blockData);
    }
}
