package dev.twme.debugstickpro.util.blockutil;

import dev.twme.debugstickpro.util.blockutil.blockdatautil.BlockDatas;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

import java.util.*;

public class BlockUtil {

    private static Map<UUID, BlockDatas> playerBlcokList = new HashMap<>();

    public static void add(UUID uuid, BlockData blockData) {
        BlockDatas blockDatas = new BlockDatas(blockData);
        playerBlcokList.put(uuid, blockDatas);
    }
    /*

    private static Map<UUID,BlcokList> playerBlcokList = new HashMap<>();

    public static void add(UUID uuid, BlockData blockData, boolean isAdd) {

        if (playerBlcokList.containsKey(uuid)) {

            BlcokList blcokList = playerBlcokList.get(uuid);

            if (isAdd) {
                blcokList.add(blockData);
            } else {
                blcokList.set(0, blockData);
            }
            playerBlcokList.put(uuid, blcokList);

        } else {
            BlcokList blcokList = new BlcokList();
            blcokList.add(blockData);
            playerBlcokList.put(uuid, blcokList);
        }
    }



    public static BlockData get(UUID uuid){
        return playerBlcokList.get(uuid).get(0);
    }
    public static BlockData get(UUID uuid, int index){
        return playerBlcokList.get(uuid).get(index);
    }
    */

    public static void remove(UUID uuid) {
        playerBlcokList.remove(uuid);
    }
}
