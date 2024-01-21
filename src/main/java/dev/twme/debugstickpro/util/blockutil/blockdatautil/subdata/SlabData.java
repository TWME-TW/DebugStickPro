package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Slab;

public class SlabData implements SubBlockData{
    private String NAME = "SlabData";
    private BlockData blockData;
    private Slab.Type type;
    public SlabData(BlockData blockData){
        this.blockData = blockData;
        this.type = ((Slab)blockData).getType();
    }

    @Override
    public String name() {
        return NAME;
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
        return "Type: " + type;
    }

    @Override
    public String getNextAsString() {
        nextData();
        return "Type: " + type;
    }

    @Override
    public String getDataAsString() {
        return type.name();
    }

    @Override
    public String getNextDataAsString() {
        nextData();
        return type.name();
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    public void nextData(){
        if (type == Slab.Type.BOTTOM){
            type = Slab.Type.DOUBLE;
        } else if (type == Slab.Type.DOUBLE){
            type = Slab.Type.TOP;
        } else {
            type = Slab.Type.BOTTOM;
        }
        ((Slab) blockData).setType(type);
    }
}
