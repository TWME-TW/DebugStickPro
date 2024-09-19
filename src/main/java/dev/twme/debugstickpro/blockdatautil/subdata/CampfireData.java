package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Campfire;

public class CampfireData extends SubBlockData {
    private boolean isSignalFire;

    public CampfireData(BlockData blockData) {
        this.blockData = blockData;
        this.isSignalFire = ((Campfire) blockData).isSignalFire();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.CampfireDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(isSignalFire);
    }

    @Override
    public SubBlockData nextData() {
        Campfire campfire = ((Campfire) blockData);
        campfire.setSignalFire(!campfire.isSignalFire());
        this.isSignalFire = campfire.isSignalFire();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Campfire) blockData).setSignalFire(isSignalFire);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new CampfireData(blockData);
    }
}
