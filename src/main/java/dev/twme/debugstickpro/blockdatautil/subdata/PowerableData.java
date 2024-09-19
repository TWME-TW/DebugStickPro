package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Powerable;

public class PowerableData extends SubBlockData {
    private boolean powered;

    public PowerableData(BlockData blockData) {
        this.blockData = blockData;
        this.powered = ((Powerable) blockData).isPowered();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.PowerableDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(powered);
    }

    @Override
    public SubBlockData nextData() {
        Powerable powerable = ((Powerable) blockData);
        powered = !powered;
        powerable.setPowered(powered);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Powerable) blockData).setPowered(powered);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new PowerableData(blockData);
    }
}
