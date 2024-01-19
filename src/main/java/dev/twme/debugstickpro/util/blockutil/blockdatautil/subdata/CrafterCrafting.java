package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Crafter;

public class CrafterCrafting implements SubBlockData{
    private String NAME = "Crafting";
    private BlockData blockData;
    private boolean crafting;

    public CrafterCrafting(BlockData blockData){
        this.blockData = blockData;
        this.crafting = ((Crafter) blockData).isCrafting();
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
        nextCraftingProperty();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Crafting: " + crafting;
    }

    @Override
    public String getNextAsString() {
        nextCraftingProperty();
        return "Crafting: " + crafting;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(crafting);
    }

    @Override
    public String getNextDataAsString() {
        nextCraftingProperty();
        return String.valueOf(crafting);
    }


    private void nextCraftingProperty(){
        Crafter crafter = ((Crafter) blockData);
        if (crafter.isCrafting()){
            crafter.setCrafting(false);
        } else {
            crafter.setCrafting(true);
        }
        this.crafting = crafter.isCrafting();
    }
}
