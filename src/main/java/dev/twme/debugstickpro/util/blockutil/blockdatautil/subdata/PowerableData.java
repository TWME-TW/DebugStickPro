package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Powerable;

public class PowerableData implements SubBlockData{
    private final String NAME = "Powerable";
    private BlockData blockData;
    private boolean powered;
    public PowerableData(BlockData blockData) {
        this.blockData = blockData;
        this.powered = ((Powerable) blockData).isPowered();
    }
    @Override
    public String NAME() {
        return NAME;
    }

    @Override
    public BlockData getData() {
        return blockData;
    }

    @Override
    public BlockData getNextData() {
        nextPowered();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Powered: " + powered;
    }

    @Override
    public String getNextAsString() {
        nextPowered();
        return "Powered: " + powered;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(powered);
    }

    @Override
    public String getNextDataAsString() {
        nextPowered();
        return String.valueOf(powered);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextPowered(){
        Powerable powerable = ((Powerable) blockData);
        powered = !powered;
        powerable.setPowered(powered);
    }
}
