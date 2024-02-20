package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.StructureBlock;

public class StructureBlockData implements SubBlockData{
    private BlockData blockData;
    private StructureBlock.Mode mode;
    private boolean isUsing = false;
    public StructureBlockData(BlockData blockData){
        this.blockData = blockData;
        this.mode = ((StructureBlock) blockData).getMode();
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
        return "Mode: " + mode.name();
    }


    @Override
    public String getDataAsString() {
        return mode.name();
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

    public SubBlockData nextData(){
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
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((StructureBlock)blockData).setMode(mode);
        return blockData;
    }
}
