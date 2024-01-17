package dev.twme.debugstickpro.util.blockutil;

import dev.twme.debugstickpro.util.blockutil.blockdatautil.BlockDatas;
import org.bukkit.block.data.BlockData;

import java.util.ArrayList;

public class BlcokList {
    private ArrayList<BlockDatas> blockDataList = new ArrayList<BlockDatas>();

    public void add(BlockData blockData) {
        BlockDatas blockDatas = new BlockDatas(blockData);
        blockDataList.add(blockDatas);
    }

    public void set(int index, BlockData blockData) {
        BlockDatas blockDatas = new BlockDatas(blockData);
        blockDataList.set(index, blockDatas);

    }


}
