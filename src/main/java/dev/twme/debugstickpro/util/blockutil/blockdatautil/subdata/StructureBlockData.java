package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.StructureBlock;

public class StructureBlockData implements SubBlockData{
    private BlockData blockData;
    private StructureBlock.Mode mode;
    public StructureBlockData(BlockData blockData){
        this.blockData = blockData;
        this.mode = ((StructureBlock) blockData).getMode();
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
    public BlockData getNextData() {
        nextData();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Mode: " + mode.name();
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "Mode: " + mode.name();
    }

    @Override
    public String getDataAsString() {
        return mode.name();
    }

    @Override
    public String getNextDataAsString() {
        nextData();
        return mode.name();
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData(){
        StructureBlock structureBlock = ((StructureBlock) blockData);
        if (mode == StructureBlock.Mode.DATA){
            mode = StructureBlock.Mode.SAVE;
        } else if (mode == StructureBlock.Mode.SAVE){
            mode = StructureBlock.Mode.LOAD;
        } else if (mode == StructureBlock.Mode.LOAD){
            mode = StructureBlock.Mode.CORNER;
        } else if (mode == StructureBlock.Mode.CORNER){
            mode = StructureBlock.Mode.DATA;
        }
        structureBlock.setMode(mode);
    }
}