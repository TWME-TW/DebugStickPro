package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.CommandBlock;

public class CommandBlockData implements SubBlockData {
    private BlockData blockData;
    private boolean conditional;
    private boolean isUsing = false;

    public CommandBlockData(BlockData blockData) {
        this.blockData = blockData;
        this.conditional = ((CommandBlock) blockData).isConditional();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.CommandBlockDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(conditional);
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
        CommandBlock commandBlock = ((CommandBlock) blockData);
        commandBlock.setConditional(!commandBlock.isConditional());
        this.conditional = commandBlock.isConditional();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((CommandBlock) blockData).setConditional(conditional);
        return blockData;
    }

    @Override
    public SubBlockData getDataFac(BlockData blockData) {
        return new CommandBlockData(blockData);
    }
}
