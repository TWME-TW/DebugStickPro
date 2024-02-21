package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Repeater;

public class RepeaterLockedData implements SubBlockData {
    private BlockData blockData;
    private boolean locked;
    private boolean isUsing = false;

    public RepeaterLockedData(BlockData blockData) {
        this.blockData = blockData;
        this.locked = ((Repeater) blockData).isLocked();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.RepeaterLockedDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(locked);
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

    public SubBlockData nextData() {
        Repeater repeater = ((Repeater) blockData);
        locked = !locked;
        repeater.setLocked(locked);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Repeater) blockData).setLocked(locked);
        return blockData;
    }
}
