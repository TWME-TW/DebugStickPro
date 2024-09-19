package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Jukebox;


public class JukeboxData extends SubBlockData {
    private boolean hasRecord;

    public JukeboxData(BlockData blockData) {
        this.blockData = blockData;
        this.hasRecord = ((Jukebox) blockData).hasRecord();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.JukeboxDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(hasRecord);
    }

    public SubBlockData nextData() {
        String blockDataString = blockData.getAsString();
        if (hasRecord) {
            blockDataString = blockDataString.replace("has_record=true", "has_record=false");
        } else {
            blockDataString = blockDataString.replace("has_record=false", "has_record=true");
        }
        hasRecord = !hasRecord;
        blockData = DebugStickPro.getInstance().getServer().createBlockData(blockDataString);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        String blockDataString = blockData.getAsString();
        if (hasRecord) {
            blockDataString = blockDataString.replace("has_record=false", "has_record=true");
        } else {
            blockDataString = blockDataString.replace("has_record=true", "has_record=false");
        }
        blockData = DebugStickPro.getInstance().getServer().createBlockData(blockDataString);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new JukeboxData(blockData);
    }
}
