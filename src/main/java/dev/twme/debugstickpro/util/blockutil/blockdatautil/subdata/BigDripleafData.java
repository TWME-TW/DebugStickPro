package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.BigDripleaf;

public class BigDripleafData implements SubBlockData {
    private String NAME = "BigDripleaf Tilt";
    private BlockData blockData;
    private BigDripleaf.Tilt tilt;

    public BigDripleafData(BlockData blockData) {
        this.blockData = blockData;
        this.tilt = ((BigDripleaf) blockData).getTilt();
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
        nextTiltType();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "BigDripleaf Tilt: " + tilt.name();
    }

    @Override
    public String getNextAsString() {
        nextTiltType();
        return "BigDripleaf Tilt: " + tilt.name();
    }

    @Override
    public String getDataAsString() {
        return tilt.name();
    }

    @Override
    public String getNextDataAsString() {
        nextTiltType();
        return tilt.name();
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }

    private void nextTiltType() {
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
    }
}
