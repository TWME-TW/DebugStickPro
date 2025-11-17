package dev.twme.debugstickpro.blockdatautil.subdata;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.DriedGhast;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;

public class DriedGhastHydrationData extends SubBlockData {
    private int hydration;

    public DriedGhastHydrationData(BlockData blockData) {
        this.blockData = blockData;
        this.hydration = ((DriedGhast) blockData).getHydration();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.DriedGhastHydrationDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(hydration);
    }

    @Override
    public SubBlockData nextData() {
        DriedGhast driedGhast = ((DriedGhast) blockData);
        if (hydration >= driedGhast.getMaximumHydration()) {
            hydration = 0;
        } else {
            hydration++;
        }
        driedGhast.setHydration(hydration);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        DriedGhast driedGhast = ((DriedGhast) blockData);
        if (hydration <= 0) {
            hydration = driedGhast.getMaximumHydration();
        } else {
            hydration--;
        }
        driedGhast.setHydration(hydration);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((DriedGhast) blockData).setHydration(hydration);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new DriedGhastHydrationData(blockData);
    }
}
