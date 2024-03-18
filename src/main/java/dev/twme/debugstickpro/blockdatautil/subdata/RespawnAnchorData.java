package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.RespawnAnchor;

public class RespawnAnchorData implements SubBlockData {
    private final BlockData blockData;
    private int charges;
    private final boolean isUsing = false;

    public RespawnAnchorData(BlockData blockData) {
        this.blockData = blockData;
        this.charges = ((RespawnAnchor) blockData).getCharges();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.RespawnAnchorDataName;
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
    public SubBlockData previousData() {
        RespawnAnchor respawnAnchor = ((RespawnAnchor) blockData);
        if (charges <= 0) {
            charges = respawnAnchor.getMaximumCharges();
        } else {
            charges--;
        }
        respawnAnchor.setCharges(charges);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((RespawnAnchor) blockData).setCharges(charges);
        return blockData;
    }

    @Override
    public SubBlockData getDataFac(BlockData blockData) {
        return new RespawnAnchorData(blockData);
    }
}
