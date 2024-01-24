package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Bed;

public class BedData implements SubBlockData {

    private BlockData blockData;
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
    public BlockData getData() {
        return blockData;
    }


    @Override
    public String getAsString() {
        return LangFile.Bed.replace("%data%", getDataAsString());
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
    public BlockData copyTo(BlockData blockData) {
        ((Bed) blockData).setPart(part);
        return blockData;
    }
}
