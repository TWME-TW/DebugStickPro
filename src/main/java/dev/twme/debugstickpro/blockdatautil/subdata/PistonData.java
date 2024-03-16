package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Piston;

public class PistonData implements SubBlockData {
    private final BlockData blockData;
    private boolean extended;
    private boolean isUsing = false;

    public PistonData(BlockData blockData) {
        this.blockData = blockData;
        this.extended = ((Piston) blockData).isExtended();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.PistonDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(extended);
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
        Piston piston = ((Piston) blockData);
        extended = !extended;
        piston.setExtended(extended);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Piston) blockData).setExtended(extended);
        return blockData;
    }
}
