package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Bed;

public class BedData implements SubBlockData{
    private String NAME = "Bed Part";
    private BlockData blockData;
    private Bed.Part part;
    public BedData(BlockData blockData) {
        this.blockData = blockData;
        this.part = ((Bed) blockData).getPart();
    }
    @Override
    public String NAME() {
        return NAME;
    }

    @Override
    public BlockData getData() {
        return blockData;
    }

    @Override
    public BlockData getNextData() {
        nextPart();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Bed Part: " + part.name();
    }

    @Override
    public String getNextAsString() {
        nextPart();
        return "Bed Part: " + part.name();
    }

    @Override
    public String getDataAsString() {
        return part.name();
    }

    @Override
    public String getNextDataAsString() {
        nextPart();
        return part.name();
    }
    private void nextPart() {
        Bed bed = (Bed) blockData;
        if (bed.getPart() == Bed.Part.FOOT) {
            bed.setPart(Bed.Part.HEAD);
        } else {
            bed.setPart(Bed.Part.FOOT);
        }
        this.part = ((Bed) blockData).getPart();
    }
}
