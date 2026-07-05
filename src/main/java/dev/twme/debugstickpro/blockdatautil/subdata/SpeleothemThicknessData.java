package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Speleothem;

public class SpeleothemThicknessData extends SubBlockData {
    private static final Speleothem.Thickness[] thicknesses = Speleothem.Thickness.values();
    private Speleothem.Thickness thickness;

    public SpeleothemThicknessData(BlockData blockData) {
        this.blockData = blockData;
        this.thickness = ((Speleothem) blockData).getThickness();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.SpeleothemThicknessDataName;
    }

    @Override
    public String getDataAsString() {
        return thickness.name();
    }

    @Override
    public SubBlockData nextData() {
        thickness = thicknesses[(thickness.ordinal() + 1) % thicknesses.length];
        ((Speleothem) blockData).setThickness(thickness);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        thickness = thicknesses[(thickness.ordinal() - 1 + thicknesses.length) % thicknesses.length];
        ((Speleothem) blockData).setThickness(thickness);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Speleothem) blockData).setThickness(thickness);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new SpeleothemThicknessData(blockData);
    }
}
