package dev.twme.debugstickpro.blockdatautil.subdata;

import com.github.retrooper.packetevents.protocol.world.states.enums.CreakingHeartState;
import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.CreakingHeart;

public class CreakingHeartStateData extends SubBlockData {
    private CreakingHeart.State state;
    private final static CreakingHeart.State[] states = CreakingHeart.State.values();

    public CreakingHeartStateData(BlockData blockData) {
        this.blockData = blockData;
        this.state = ((CreakingHeart) blockData).getCreakingHeartState();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.CreakingHeartStateDataName;
    }

    @Override
    public String getDataAsString() {
        return String.valueOf(state);
    }

    @Override
    public SubBlockData nextData() {
        this.state = states[(state.ordinal() + 1) % states.length];
        ((CreakingHeart) blockData).setCreakingHeartState(state);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        this.state = states[(state.ordinal() - 1 + states.length) % states.length];
        ((CreakingHeart) blockData).setCreakingHeartState(state);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((CreakingHeart) blockData).setCreakingHeartState(state);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new CreakingHeartStateData(blockData);
    }
}
