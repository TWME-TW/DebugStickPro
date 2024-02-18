package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.ChiseledBookshelf;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ChiseledBookshelfInventory;
import org.bukkit.inventory.ItemStack;

public class ChiseledBookshelfSlot_Beta implements SubBlockData{
    private String NAME = "Bookshelf Slot 5";
    private Block block;
    private boolean isUsing = false;
    public ChiseledBookshelfSlot_Beta(Block block){
        this.block = block;
    }
    @Override
    public String name() {
        return NAME;
    }

    @Override
    public BlockData getData() {
        return block.getBlockData();
    }


    @Override
    public String getAsString() {
        return "Slot 5: " + ((org.bukkit.block.data.type.ChiseledBookshelf) block.getBlockData()).isSlotOccupied(5);
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(((org.bukkit.block.data.type.ChiseledBookshelf) block.getBlockData()).isSlotOccupied(5));
    }


    @Override
    public SubBlockData setIsUsing(boolean isUsing) {
        return null;
    }

    @Override
    public boolean isUsing() {
        return false;
    }

    @Override
    public SubBlockData nextData() {
        return null;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        return null;
    }

    private void nextSlot(){
        ChiseledBookshelfInventory cf = ((ChiseledBookshelf) block.getState()).getInventory();
        if (cf.getItem(5) == null) {
            ItemStack itemStack = new ItemStack(Material.BOOK);
            cf.setItem(5, itemStack);
        } else {
            cf.setItem(5, null);
        }
    }
}
