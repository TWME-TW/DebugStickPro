package dev.twme.debugstickpro.util.blockdatautil;

import org.bukkit.block.data.BlockData;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class BlockDataUtil {
    private static Map playerBlcokDataMap = new TreeMap<UUID, BlcokDataList>();

    public static void add(UUID uuid, BlockData blockData, boolean isAdd) {
        if (playerBlcokDataMap.containsKey(uuid)) {

            if (isAdd) {
                // add
                BlcokDataList blcokDataList = (BlcokDataList) playerBlcokDataMap.get(uuid);
                blcokDataList.set(blockData);
            } else {
                // replace
                BlcokDataList blcokDataList = (BlcokDataList) playerBlcokDataMap.get(uuid);
                blcokDataList.add(blockData);
            }
        } else {
            BlcokDataList blcokDataList = new BlcokDataList();
            blcokDataList.add(blockData);
            playerBlcokDataMap.put(uuid, blcokDataList);
        }
    }
}
