package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Dispenser;

public class DispenserData extends SubBlockData {
    private boolean triggered;

    public DispenserData(BlockData blockData) {
        this.blockData = blockData;
        this.triggered = ((Dispenser) blockData).isTriggered();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.DispenserDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(triggered);
    }

    @Override
    public SubBlockData nextData() {
        Dispenser dispenser = ((Dispenser) blockData);
        dispenser.setTriggered(!dispenser.isTriggered());
        this.triggered = dispenser.isTriggered();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Dispenser) blockData).setTriggered(triggered);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new DispenserData(blockData);
    }
}
