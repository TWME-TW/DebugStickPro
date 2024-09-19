package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Crafter;

public class CrafterTriggerData extends SubBlockData {
    private boolean triggered;

    public CrafterTriggerData(BlockData blockData) {
        this.blockData = blockData;
        this.triggered = ((Crafter) blockData).isTriggered();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.CrafterTriggerDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(triggered);
    }

    @Override
    public SubBlockData nextData() {
        Crafter crafter = ((Crafter) blockData);
        crafter.setTriggered(!crafter.isTriggered());
        this.triggered = crafter.isTriggered();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Crafter) blockData).setTriggered(triggered);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new CrafterTriggerData(blockData);
    }
}
