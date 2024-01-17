package dev.twme.debugstickpro.util.blockdatautil;

import org.bukkit.block.data.BlockData;

import java.util.ArrayList;

public class BlcokDataList {
    private ArrayList<BlockData> blockDataList = new ArrayList<BlockData>();

    public void add(BlockData blockData) {
        blockDataList.add(blockData);
    }

    public void remove(BlockData blockData) {
        blockDataList.remove(blockData);
    }

    public void set(BlockData blockData) {
        blockDataList.set( 0, blockData);
    }


}
