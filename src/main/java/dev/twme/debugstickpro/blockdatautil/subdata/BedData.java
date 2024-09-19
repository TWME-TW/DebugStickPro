package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Bed;

public class BedData extends SubBlockData {
    private Bed.Part part;

    public BedData(BlockData blockData) {
        this.blockData = blockData;
        this.part = ((Bed) blockData).getPart();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.BedDataName;
    }


    @Override
    public String getDataAsString() {
        return part.name();
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
        nextData();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Bed) blockData).setPart(part);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new BedData(blockData);
    }
}
