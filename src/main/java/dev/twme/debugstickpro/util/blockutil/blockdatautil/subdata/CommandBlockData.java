package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.CommandBlock;

public class CommandBlockData implements SubBlockData{
    private String NAME = "Command Block";
    private BlockData blockData;
    private boolean conditional;
    public CommandBlockData(BlockData blockData){
        this.blockData = blockData;
        this.conditional = ((CommandBlock) blockData).isConditional();
    }

    @Override
    public String NAME() {
        return NAME;
    }

    @Override
    public BlockData getData() {
        return blockData;
    }

    @Override
    public BlockData getNextData() {
        nextConditionalProperty();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Conditional: " + conditional;
    }

    @Override
    public String getNextAsString() {
        nextConditionalProperty();
        return "Conditional: " + conditional;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(conditional);
    }

    @Override
    public String getNextDataAsString() {
        nextConditionalProperty();
        return String.valueOf(conditional);
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextConditionalProperty(){
        CommandBlock commandBlock = ((CommandBlock) blockData);
        commandBlock.setConditional(!commandBlock.isConditional());
        this.conditional = commandBlock.isConditional();
    }
}
