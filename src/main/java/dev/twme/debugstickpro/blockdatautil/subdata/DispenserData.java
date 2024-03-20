package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Dispenser;

public class DispenserData implements SubBlockData {
    private final BlockData blockData;
    private boolean triggered;
    private boolean isUsing = false;

    public DispenserData(BlockData blockData) {
        this.blockData = blockData;
        this.triggered = ((Dispenser) blockData).isTriggered();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.DispenserDataName;
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
    public SubBlockData getDataFac(BlockData blockData) {
        return new DispenserData(blockData);
    }
}
