package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.CommandBlock;
import org.bukkit.material.SmoothBrick;

public class CommandBlockData implements SubBlockData{
        private BlockData blockData;
    private boolean conditional;
    private boolean isUsing = false;
    public CommandBlockData(BlockData blockData){
        this.blockData = blockData;
        this.conditional = ((CommandBlock) blockData).isConditional();
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
        return LangFile.CommandBlockData.replace("%data%",getDataAsString());
    }


    @Override
    public String getDataAsString() {
        return String.valueOf(conditional);
    }

    @Override
    public SubBlockData setIsUsing(boolean isUsing){
        this.isUsing = isUsing;
        return this;
    }

    @Override
    public boolean isUsing() {
        return isUsing;
    }
    @Override
    public SubBlockData nextData(){
        CommandBlock commandBlock = ((CommandBlock) blockData);
        commandBlock.setConditional(!commandBlock.isConditional());
        this.conditional = commandBlock.isConditional();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((CommandBlock)blockData).setConditional(conditional);
        return blockData;
    }
}
