package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Lectern;

public class LecternData extends SubBlockData {
    private boolean hasBook;

    public LecternData(BlockData blockData) {
        this.blockData = blockData;
        this.hasBook = ((Lectern) blockData).hasBook();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.LectronDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(hasBook);
    }

    @Override
    public SubBlockData nextData() {
        String blockDataString = this.blockData.getAsString();
        if (hasBook) {
            blockDataString = blockDataString.replace("has_book=true", "has_book=false");
        } else {
            blockDataString = blockDataString.replace("has_book=false", "has_book=true");
        }
        this.blockData = DebugStickPro.getInstance().getServer().createBlockData(blockDataString);
        hasBook = !hasBook;
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        String blockDataString = blockData.getAsString();
        if (hasBook) {
            blockDataString.replace("has_book=false", "has_book=true");
        } else {
            blockDataString.replace("has_book=true", "has_book=false");
        }
        blockData = DebugStickPro.getInstance().getServer().createBlockData(blockDataString);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new LecternData(blockData);
    }
}
