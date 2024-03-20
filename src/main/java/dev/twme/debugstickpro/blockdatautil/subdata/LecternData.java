package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Lectern;

public class LecternData implements SubBlockData {
    private BlockData blockData;
    private boolean hasBook;
    private boolean isUsing = false;

    public LecternData(BlockData blockData) {
        this.blockData = blockData;
        this.hasBook = ((Lectern) blockData).hasBook();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.LectronDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(hasBook);
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
    public SubBlockData getDataFac(BlockData blockData) {
        return new LecternData(blockData);
    }
}
