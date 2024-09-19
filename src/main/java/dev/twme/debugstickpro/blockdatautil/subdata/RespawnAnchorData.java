package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.RespawnAnchor;

public class RespawnAnchorData extends SubBlockData {
    private int charges;

    public RespawnAnchorData(BlockData blockData) {
        this.blockData = blockData;
        this.charges = ((RespawnAnchor) blockData).getCharges();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.RespawnAnchorDataName;
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
    public SubBlockData fromBlockData(BlockData blockData) {
        return new RespawnAnchorData(blockData);
    }
}
