package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Tripwire;

public class TripwireData extends SubBlockData {
    private boolean isDisarmed;

    public TripwireData(BlockData blockData) {
        this.blockData = blockData;
        this.isDisarmed = ((Tripwire) blockData).isDisarmed();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.TripwireDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(isDisarmed);
    }

    public SubBlockData nextData() {
        Tripwire tripwire = ((Tripwire) blockData);
        isDisarmed = !isDisarmed;
        tripwire.setDisarmed(isDisarmed);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        return nextData();
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((Tripwire) blockData).setDisarmed(isDisarmed);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new TripwireData(blockData);
    }
}
