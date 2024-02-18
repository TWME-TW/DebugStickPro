package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Crafter;

public class CrafterCrafting implements SubBlockData{
    private BlockData blockData;
    private boolean crafting;
    private boolean isUsing = false;

    public CrafterCrafting(BlockData blockData){
        this.blockData = blockData;
        this.crafting = ((Crafter) blockData).isCrafting();
    }
    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public BlockData getData() {
        return blockData;
    }


    @Override
    public String getAsString() {
        return "Crafting: " + crafting;
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


    public SubBlockData nextData(){
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
