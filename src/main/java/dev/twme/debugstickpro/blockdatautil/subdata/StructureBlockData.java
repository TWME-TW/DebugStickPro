package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.StructureBlock;

public class StructureBlockData extends SubBlockData {
    private StructureBlock.Mode mode;

    public StructureBlockData(BlockData blockData) {
        this.blockData = blockData;
        this.mode = ((StructureBlock) blockData).getMode();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.StructureBlockDataName;
    }


    @Override
    public String getDataAsString() {
        return mode.name();
    }

    public SubBlockData nextData() {
        StructureBlock structureBlock = ((StructureBlock) blockData);
        if (mode == StructureBlock.Mode.LOAD) {
            mode = StructureBlock.Mode.SAVE;
        } else if (mode == StructureBlock.Mode.SAVE) {
            mode = StructureBlock.Mode.DATA;
        } else if (mode == StructureBlock.Mode.DATA) {
            mode = StructureBlock.Mode.CORNER;
        } else if (mode == StructureBlock.Mode.CORNER) {
            mode = StructureBlock.Mode.LOAD;
        }
        structureBlock.setMode(mode);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        StructureBlock structureBlock = ((StructureBlock) blockData);
        if (mode == StructureBlock.Mode.SAVE) {
            mode = StructureBlock.Mode.LOAD;
        } else if (mode == StructureBlock.Mode.LOAD) {
            mode = StructureBlock.Mode.DATA;
        } else if (mode == StructureBlock.Mode.DATA) {
            mode = StructureBlock.Mode.CORNER;
        } else if (mode == StructureBlock.Mode.CORNER) {
            mode = StructureBlock.Mode.SAVE;
        }
        structureBlock.setMode(mode);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((StructureBlock) blockData).setMode(mode);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new StructureBlockData(blockData);
    }
}
