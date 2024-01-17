package dev.twme.debugstickpro.util.blockutil.blockdatautil;

import dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata.SubBlockData;
import org.bukkit.block.data.BlockData;

import java.util.ArrayList;

public class BlockDatas {
    private ArrayList<SubBlockData> blockDatas = new ArrayList<SubBlockData>();
    public BlockDatas(BlockData blockData) {
        blockDatas.addAll(BlockDataSeparater.Separate(blockData));
    }

    public void add(BlockData blockData) {
        blockDatas.addAll(BlockDataSeparater.Separate(blockData));
    }

    public SubBlockData[] get() {
        SubBlockData[] array = (SubBlockData[]) blockDatas.toArray();
        return array;
    }


}
