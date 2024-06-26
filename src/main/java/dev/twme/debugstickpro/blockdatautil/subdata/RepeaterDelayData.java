package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Repeater;

public class RepeaterDelayData implements SubBlockData {
    private final BlockData blockData;
    private int delay;
    private boolean isUsing = false;

    public RepeaterDelayData(BlockData blockData) {
        this.blockData = blockData;
        this.delay = ((Repeater) blockData).getDelay();
    }


    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.RepeaterDelayDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(delay);
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
        delay = repeater.getDelay();
        if (delay >= repeater.getMaximumDelay()) {
            delay = repeater.getMinimumDelay();
        } else {
            delay++;
        }
        repeater.setDelay(delay);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Repeater repeater = ((Repeater) blockData);
        delay = repeater.getDelay();
        if (delay <= repeater.getMinimumDelay()) {
            delay = repeater.getMaximumDelay();
        } else {
            delay--;
        }
        repeater.setDelay(delay);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Repeater) blockData).setDelay(delay);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new RepeaterDelayData(blockData);
    }
}
