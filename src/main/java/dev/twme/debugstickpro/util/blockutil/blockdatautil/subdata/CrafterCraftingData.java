package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Crafter;

public class CrafterCraftingData implements SubBlockData {
    private BlockData blockData;
    private boolean crafting;
    private boolean isUsing = false;

    public CrafterCraftingData(BlockData blockData) {
        this.blockData = blockData;
        this.crafting = ((Crafter) blockData).isCrafting();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.CrafterCraftingDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(crafting);
    }


    @Override
    public SubBlockData setIsUsing(boolean isUsing) {
        this.isUsing = isUsing;
        return this;
    }

    @Override
    public boolean isUsing() {
        return false;
    }


    public SubBlockData nextData() {
        Crafter crafter = ((Crafter) blockData);
        crafter.setCrafting(!crafter.isCrafting());
        this.crafting = crafter.isCrafting();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        return null;
    }
}