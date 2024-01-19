package dev.twme.debugstickpro.util.blockutil.blockdatautil;

import dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata.SubBlockData;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

import java.util.ArrayList;

public class BlockDatas {
    private ArrayList<SubBlockData> blockDatas = new ArrayList<SubBlockData>();
    private BlockData blockData;

    public BlockDatas(Block block) {
        blockDatas.addAll(BlockDataSeparater.Separate(block));
    }

    public ArrayList<SubBlockData> getBlockDatas() {
        return blockDatas;
    }

    public String getAsString() {
        StringBuilder str = new StringBuilder();
        for (SubBlockData subBlockData : blockDatas) {
            str.append(subBlockData.getAsString()).append(" ");
        }
        return str.toString();
    }
}
