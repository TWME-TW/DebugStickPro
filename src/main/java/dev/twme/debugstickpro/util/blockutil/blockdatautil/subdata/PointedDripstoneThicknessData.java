package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.PointedDripstone;

public class PointedDripstoneThicknessData implements SubBlockData{
    private final String NAME = "PointedDripstoneThickness";
    private BlockData blockData;
    private PointedDripstone.Thickness thickness;
    private boolean isUsing = false;
    public PointedDripstoneThicknessData(BlockData blockData) {
        this.blockData = blockData;
        this.thickness = ((PointedDripstone) blockData).getThickness();
    }
    @Override
    public String name() {
        return NAME;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getAsString() {
        return "Thickness: " + thickness;
    }



    @Override
    public String getDataAsString() {
        return thickness.name();
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

    public SubBlockData nextData(){
        PointedDripstone pointedDripstone = ((PointedDripstone) blockData);
        thickness = PointedDripstone.Thickness.values()[(thickness.ordinal() + 1) % PointedDripstone.Thickness.values().length];
        pointedDripstone.setThickness(thickness);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        return null;
    }
}
