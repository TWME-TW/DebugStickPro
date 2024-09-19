package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.PointedDripstone;

public class PointedDripstoneThicknessData extends SubBlockData {
    private PointedDripstone.Thickness thickness;

    public PointedDripstoneThicknessData(BlockData blockData) {
        this.blockData = blockData;
        this.thickness = ((PointedDripstone) blockData).getThickness();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.PointedDripstoneThicknessDataName;
    }

    @Override
    public String getDataAsString() {
        return thickness.name();
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
    public SubBlockData fromBlockData(BlockData blockData) {
        return new PointedDripstoneThicknessData(blockData);
    }
}
