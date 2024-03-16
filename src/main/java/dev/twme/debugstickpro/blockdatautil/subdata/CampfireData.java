package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Campfire;

public class CampfireData implements SubBlockData {
    private BlockData blockData;
    private boolean isSignalFire;
    private boolean isUsing = false;

    public CampfireData(BlockData blockData) {
        this.blockData = blockData;
        this.isSignalFire = ((Campfire) blockData).isSignalFire();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.CampfireDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(isSignalFire);
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
        Campfire campfire = ((Campfire) blockData);
        campfire.setSignalFire(!campfire.isSignalFire());
        this.isSignalFire = campfire.isSignalFire();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Campfire) blockData).setSignalFire(isSignalFire);
        return blockData;
    }
}
