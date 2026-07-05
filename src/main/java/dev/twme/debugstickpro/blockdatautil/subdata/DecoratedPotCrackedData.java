package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.DecoratedPot;

public class DecoratedPotCrackedData extends SubBlockData {
    private boolean cracked;

    public DecoratedPotCrackedData(BlockData blockData) {
        this.blockData = blockData;
        this.cracked = ((DecoratedPot) blockData).isCracked();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.DecoratedPotCrackedDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(cracked);
    }

    @Override
    public SubBlockData nextData() {
        cracked = !cracked;
        ((DecoratedPot) blockData).setCracked(cracked);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((DecoratedPot) blockData).setCracked(cracked);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new DecoratedPotCrackedData(blockData);
    }
}
