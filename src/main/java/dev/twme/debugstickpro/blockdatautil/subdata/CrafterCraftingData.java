package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Crafter;

public class CrafterCraftingData extends SubBlockData {
    private boolean crafting;

    public CrafterCraftingData(BlockData blockData) {
        this.blockData = blockData;
        this.crafting = ((Crafter) blockData).isCrafting();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.CrafterCraftingDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(crafting);
    }

    public SubBlockData nextData() {
        Crafter crafter = ((Crafter) blockData);
        crafter.setCrafting(!crafter.isCrafting());
        this.crafting = crafter.isCrafting();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Crafter) blockData).setCrafting(crafting);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new CrafterCraftingData(blockData);
    }
}
