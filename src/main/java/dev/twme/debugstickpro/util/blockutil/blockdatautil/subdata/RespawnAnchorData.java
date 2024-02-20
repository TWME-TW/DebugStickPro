package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.RespawnAnchor;

public class RespawnAnchorData implements SubBlockData {
    private String NAME = "RespawnAnchor";
    private BlockData blockData;
    private int charges;
    private boolean isUsing = false;

    public RespawnAnchorData(BlockData blockData) {
        this.blockData = blockData;
        this.charges = ((RespawnAnchor) blockData).getCharges();
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String dataName() {
        return LangFile.RespawnAnchorDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(charges);
    }

    @Override
    public SubBlockData setIsUsing(boolean isUsing) {
        return this;
    }

    @Override
    public boolean isUsing() {
        return isUsing;
    }

    @Override
    public SubBlockData nextData() {
        RespawnAnchor respawnAnchor = ((RespawnAnchor) blockData);
        if (charges >= respawnAnchor.getMaximumCharges()) {
            charges = 0;
        } else {
            charges++;
        }
        respawnAnchor.setCharges(charges);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((RespawnAnchor) blockData).setCharges(charges);
        return blockData;
    }
}
