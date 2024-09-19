package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Piston;

public class PistonData extends SubBlockData {
    private boolean extended;

    public PistonData(BlockData blockData) {
        this.blockData = blockData;
        this.extended = ((Piston) blockData).isExtended();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.PistonDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(extended);
    }

    @Override
    public SubBlockData nextData() {
        Piston piston = ((Piston) blockData);
        extended = !extended;
        piston.setExtended(extended);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Piston) blockData).setExtended(extended);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new PistonData(blockData);
    }
}
