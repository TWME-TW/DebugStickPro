package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Scaffolding;

public class ScaffoldingBottomData implements SubBlockData {
    private String NAME = "ScaffoldingBottom";
    private BlockData blockData;
    private boolean bottom;
    private boolean isUsing = false;

    public ScaffoldingBottomData(BlockData blockData) {
        this.blockData = blockData;
        this.bottom = ((Scaffolding) blockData).isBottom();
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String dataName() {
        return LangFile.ScaffoldingBottomDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(bottom);
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
        Scaffolding scaffolding = ((Scaffolding) blockData);
        bottom = !bottom;
        scaffolding.setBottom(bottom);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Scaffolding) blockData).setBottom(bottom);
        return blockData;
    }
}
