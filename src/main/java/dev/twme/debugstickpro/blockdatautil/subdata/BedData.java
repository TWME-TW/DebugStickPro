package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Bed;

public class BedData implements SubBlockData {

    private final BlockData blockData;
    private Bed.Part part;
    private boolean isUsing = false;

    public BedData(BlockData blockData) {
        this.blockData = blockData;
        this.part = ((Bed) blockData).getPart();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.BedDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return part.name();
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
        Bed bed = (Bed) blockData;
        if (bed.getPart() == Bed.Part.FOOT) {
            bed.setPart(Bed.Part.HEAD);
        } else {
            bed.setPart(Bed.Part.FOOT);
        }
        this.part = ((Bed) blockData).getPart();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        Bed bed = (Bed) blockData;
        if (bed.getPart() == Bed.Part.FOOT) {
            bed.setPart(Bed.Part.HEAD);
        } else {
            bed.setPart(Bed.Part.FOOT);
        }
        this.part = ((Bed) blockData).getPart();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Bed) blockData).setPart(part);
        return blockData;
    }

    @Override
    public SubBlockData getDataFac(BlockData blockData) {
        return new BedData(blockData);
    }
}
