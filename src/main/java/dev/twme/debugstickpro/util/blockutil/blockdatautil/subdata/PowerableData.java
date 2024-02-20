package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Powerable;

public class PowerableData implements SubBlockData {
    private final String NAME = "Powerable";
    private BlockData blockData;
    private boolean powered;
    private boolean isUsing = false;

    public PowerableData(BlockData blockData) {
        this.blockData = blockData;
        this.powered = ((Powerable) blockData).isPowered();
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String dataName() {
        return LangFile.PowerableDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(powered);
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
        Powerable powerable = ((Powerable) blockData);
        powered = !powered;
        powerable.setPowered(powered);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Powerable) blockData).setPowered(powered);
        return blockData;
    }
}
