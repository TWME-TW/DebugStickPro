package dev.twme.debugstickpro.blockdatautil.subdata;

import java.util.Arrays;
import java.util.List;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.SideChaining;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;

public class SideChainingData extends SubBlockData {
    private SideChaining.ChainPart chainPart;

    public SideChainingData(BlockData blockData) {
        this.blockData = blockData;
        this.chainPart = ((SideChaining) blockData).getSideChain();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.SideChainingDataName;
    }

    @Override
    public String getDataAsString() {
        return chainPart.name();
    }

    @Override
    public SubBlockData nextData() {
        List<SideChaining.ChainPart> chainParts = Arrays.asList(SideChaining.ChainPart.values());
        int index = chainParts.indexOf(chainPart);
        if (index >= chainParts.size() - 1) {
            ((SideChaining) blockData).setSideChain(chainParts.getFirst());
        } else {
            ((SideChaining) blockData).setSideChain(chainParts.get(index + 1));
        }
        this.chainPart = ((SideChaining) blockData).getSideChain();
        return this;
    }

    @Override
    public SubBlockData previousData() {
        List<SideChaining.ChainPart> chainParts = Arrays.asList(SideChaining.ChainPart.values());
        int index = chainParts.indexOf(chainPart);
        if (index <= 0) {
            ((SideChaining) blockData).setSideChain(chainParts.getLast());
        } else {
            ((SideChaining) blockData).setSideChain(chainParts.get(index - 1));
        }
        this.chainPart = ((SideChaining) blockData).getSideChain();
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((SideChaining) blockData).setSideChain(chainPart);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new SideChainingData(blockData);
    }
}
