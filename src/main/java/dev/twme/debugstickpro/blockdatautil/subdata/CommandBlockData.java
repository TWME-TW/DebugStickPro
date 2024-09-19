package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.CommandBlock;

public class CommandBlockData extends SubBlockData {
    private boolean conditional;

    public CommandBlockData(BlockData blockData) {
        this.blockData = blockData;
        this.conditional = ((CommandBlock) blockData).isConditional();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.CommandBlockDataName;
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(conditional);
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
    public SubBlockData fromBlockData(BlockData blockData) {
        return new CommandBlockData(blockData);
    }
}
