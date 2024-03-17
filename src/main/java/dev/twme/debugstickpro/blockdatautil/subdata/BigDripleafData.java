package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.BigDripleaf;

public class BigDripleafData implements SubBlockData {
    private BlockData blockData;
    private BigDripleaf.Tilt tilt;
    private boolean isUsing = false;

    public BigDripleafData(BlockData blockData) {
        this.blockData = blockData;
        this.tilt = ((BigDripleaf) blockData).getTilt();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.BigDripleafDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return tilt.name();
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
        BigDripleaf bigDripleaf = ((BigDripleaf) blockData);

        if (bigDripleaf.getTilt() == BigDripleaf.Tilt.FULL) {
            bigDripleaf.setTilt(BigDripleaf.Tilt.NONE);
        } else if (bigDripleaf.getTilt() == BigDripleaf.Tilt.NONE) {
            bigDripleaf.setTilt(BigDripleaf.Tilt.PARTIAL);
        } else if (bigDripleaf.getTilt() == BigDripleaf.Tilt.PARTIAL) {
            bigDripleaf.setTilt(BigDripleaf.Tilt.UNSTABLE);
        } else {
            bigDripleaf.setTilt(BigDripleaf.Tilt.UNSTABLE);
        }
        this.tilt = bigDripleaf.getTilt();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        BigDripleaf bigDripleaf = ((BigDripleaf) blockData);

        if (bigDripleaf.getTilt() == BigDripleaf.Tilt.UNSTABLE) {
            bigDripleaf.setTilt(BigDripleaf.Tilt.PARTIAL);
        } else if (bigDripleaf.getTilt() == BigDripleaf.Tilt.PARTIAL) {
            bigDripleaf.setTilt(BigDripleaf.Tilt.NONE);
        } else if (bigDripleaf.getTilt() == BigDripleaf.Tilt.NONE) {
            bigDripleaf.setTilt(BigDripleaf.Tilt.FULL);
        } else {
            bigDripleaf.setTilt(BigDripleaf.Tilt.FULL);
        }
        this.tilt = bigDripleaf.getTilt();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((BigDripleaf) blockData).setTilt(tilt);
        return blockData;
    }

    @Override
    public SubBlockData getDataFac(BlockData blockData) {
        return new BigDripleafData(blockData);
    }
}
