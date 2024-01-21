package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.PointedDripstone;

public class PointedDripstoneThicknessData implements SubBlockData{
    private final String NAME = "PointedDripstoneThickness";
    private BlockData blockData;
    private PointedDripstone.Thickness thickness;
    public PointedDripstoneThicknessData(BlockData blockData) {
        this.blockData = blockData;
        this.thickness = ((PointedDripstone) blockData).getThickness();
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
        nextThickness();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Thickness: " + thickness;
    }

    @Override
    public String getNextAsString() {
        nextThickness();
        return "Thickness: " + thickness;
    }

    @Override
    public String getDataAsString() {
        return thickness.name();
    }

    @Override
    public String getNextDataAsString() {
        nextThickness();
        return thickness.name();
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextThickness(){
        PointedDripstone pointedDripstone = ((PointedDripstone) blockData);
        thickness = PointedDripstone.Thickness.values()[(thickness.ordinal() + 1) % PointedDripstone.Thickness.values().length];
        pointedDripstone.setThickness(thickness);
    }
}
