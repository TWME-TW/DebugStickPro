package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Crafter;

public class CrafterTriggerData implements SubBlockData {
    private BlockData blockData;
    private boolean triggered;
    private boolean isUsing = false;

    public CrafterTriggerData(BlockData blockData) {
        this.blockData = blockData;
        this.triggered = ((Crafter) blockData).isTriggered();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.CrafterTriggerDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(triggered);
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
        Crafter crafter = ((Crafter) blockData);
        crafter.setTriggered(!crafter.isTriggered());
        this.triggered = crafter.isTriggered();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Crafter) blockData).setTriggered(triggered);
        return blockData;
    }
}
