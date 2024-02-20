package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.MultipleFacing;

public class MultipleFacingEastSouthEastData implements SubBlockData {
    private BlockData blockData;
    private boolean isUsing = false;
    private boolean has;
    final BlockFace face = BlockFace.EAST_SOUTH_EAST;

    public MultipleFacingEastSouthEastData(BlockData blockData) {
        this.blockData = blockData;
        this.has = ((MultipleFacing) blockData).hasFace(face);
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
        return "Face: " + face + " Has: " + has;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(has);
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
        MultipleFacing blockData = ((MultipleFacing) this.blockData);
        blockData.setFace(face, !has);
        has = !has;
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((MultipleFacing) blockData).setFace(face, has);
        return blockData;
    }
}
