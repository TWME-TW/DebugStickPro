package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.RespawnAnchor;

public class RespawnAnchorData implements SubBlockData{
    private String NAME = "RespawnAnchor";
    private BlockData blockData;
    private int charges;
    public RespawnAnchorData(BlockData blockData) {
        this.blockData = blockData;
        this.charges = ((RespawnAnchor) blockData).getCharges();
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
        nextData();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Charges: " + charges;
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "Charges: " + charges;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(charges);
    }

    @Override
    public String getNextDataAsString() {
        nextData();
        return String.valueOf(charges);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData(){
        RespawnAnchor respawnAnchor = ((RespawnAnchor) blockData);
        if (charges >= respawnAnchor.getMaximumCharges()){
            charges = 0;
        } else {
            charges++;
        }
        respawnAnchor.setCharges(charges);
    }
}
