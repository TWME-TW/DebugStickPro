package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.MultipleFacing;

public abstract class MultipleFacingData extends SubBlockData {
    protected BlockFace face;
    protected boolean has;

    public MultipleFacingData(BlockData blockData, BlockFace face) {
        super.blockData = blockData;
        this.face = face;
        this.has = ((MultipleFacing) blockData).hasFace(face);
    }

    public String getDataAsString() {
        return String.valueOf(has);
    }

    @Override
    public SubBlockData nextData() {
        MultipleFacing blockData = ((MultipleFacing) this.blockData);
        blockData.setFace(face, !has);
        has = !has;
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((MultipleFacing) blockData).setFace(face, has);
        return blockData;
    }
}
