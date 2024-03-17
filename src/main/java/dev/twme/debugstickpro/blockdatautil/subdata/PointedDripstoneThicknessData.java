package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.PointedDripstone;

public class PointedDripstoneThicknessData implements SubBlockData {
    private BlockData blockData;
    private PointedDripstone.Thickness thickness;
    private boolean isUsing = false;

    public PointedDripstoneThicknessData(BlockData blockData) {
        this.blockData = blockData;
        this.thickness = ((PointedDripstone) blockData).getThickness();
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return LangFile.DataKeyName.PointedDripstoneThicknessDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
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

    public SubBlockData nextData() {
        PointedDripstone pointedDripstone = ((PointedDripstone) blockData);
        thickness = PointedDripstone.Thickness.values()[(thickness.ordinal() + 1) % PointedDripstone.Thickness.values().length];
        pointedDripstone.setThickness(thickness);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        PointedDripstone pointedDripstone = ((PointedDripstone) blockData);
        thickness = PointedDripstone.Thickness.values()[(thickness.ordinal() - 1 + PointedDripstone.Thickness.values().length) % PointedDripstone.Thickness.values().length];
        pointedDripstone.setThickness(thickness);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((PointedDripstone) blockData).setThickness(thickness);
        return blockData;
    }

    @Override
    public SubBlockData getDataFac(BlockData blockData) {
        return new PointedDripstoneThicknessData(blockData);
    }
}
