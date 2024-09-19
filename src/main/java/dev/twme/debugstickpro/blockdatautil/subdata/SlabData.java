package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Slab;

public class SlabData extends SubBlockData {
    private Slab.Type type;

    public SlabData(BlockData blockData) {
        this.blockData = blockData;
        this.type = ((Slab) blockData).getType();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.SlabDataName;
    }

    @Override
    public String getDataAsString() {
        return type.name();
    }

    public SubBlockData nextData() {
        if (type == Slab.Type.BOTTOM) {
            type = Slab.Type.DOUBLE;
        } else if (type == Slab.Type.DOUBLE) {
            type = Slab.Type.TOP;
        } else {
            type = Slab.Type.BOTTOM;
        }
        ((Slab) blockData).setType(type);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        if (type == Slab.Type.TOP) {
            type = Slab.Type.DOUBLE;
        } else if (type == Slab.Type.DOUBLE) {
            type = Slab.Type.BOTTOM;
        } else {
            type = Slab.Type.TOP;
        }
        ((Slab) blockData).setType(type);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Slab) blockData).setType(type);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new SlabData(blockData);
    }
}
