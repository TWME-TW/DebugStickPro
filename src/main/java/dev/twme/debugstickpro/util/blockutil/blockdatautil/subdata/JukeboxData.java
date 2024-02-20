package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.DebugStickPro;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Jigsaw;
import org.bukkit.block.data.type.Jukebox;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.JukeboxInventory;


public class JukeboxData implements SubBlockData {
    private BlockData blockData;
    private boolean hasRecord;
    private boolean isUsing = false;

    public JukeboxData(BlockData blockData) {
        this.blockData = blockData;
        this.hasRecord = ((Jukebox) blockData).hasRecord();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getAsString() {
        return "Has Record: " + hasRecord;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(hasRecord);
    }


    @Override
    public SubBlockData setIsUsing(boolean isUsing) {
        this.isUsing = isUsing;
        return this;
    }

    @Override
    public boolean isUsing() {
        return isUsing;
    }

    public SubBlockData nextData() {
        String blockDataString = blockData.getAsString();
        if (hasRecord) {
            blockDataString.replace("has_record=true", "has_record=false");
        } else {
            blockDataString.replace("has_record=false", "has_record=true");
        }
        hasRecord = !hasRecord;
        blockData = DebugStickPro.getInstance().getServer().createBlockData(blockDataString);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        String blockDataString = blockData.getAsString();
        if (hasRecord) {
            blockDataString.replace("has_record=false", "has_record=true");
        } else {
            blockDataString.replace("has_record=true", "has_record=false");
        }
        blockData = DebugStickPro.getInstance().getServer().createBlockData(blockDataString);
        return blockData;
    }
}
