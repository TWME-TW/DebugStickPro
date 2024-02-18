package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.JukeboxInventory;


public class JukeboxData implements SubBlockData{
    private BlockData blockData;
    private boolean hasRecord;
    private JukeboxInventory jukeboxInventory;
    private boolean isUsing = false;
    public JukeboxData(Block block){
        jukeboxInventory = ((org.bukkit.block.Jukebox)block.getState()).getInventory();
        this.blockData = block.getBlockData();
        this.hasRecord = ((org.bukkit.block.data.type.Jukebox)blockData).hasRecord();
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public BlockData getData() {
        return null;
    }


    @Override
    public String getAsString() {
        return null;
    }


    @Override
    public String getDataAsString() {
        return null;
    }


    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData(){
        ItemStack cd = jukeboxInventory.getItem(0);

        if (!hasRecord) {
            jukeboxInventory.setRecord(null);
        } else {

        }

    }
}
