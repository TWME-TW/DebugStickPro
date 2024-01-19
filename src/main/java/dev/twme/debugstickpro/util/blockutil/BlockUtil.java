package dev.twme.debugstickpro.util.blockutil;

import dev.twme.debugstickpro.util.blockutil.blockdatautil.BlockDatas;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

import java.util.*;

public class BlockUtil {

    private static Map<UUID, BlockDatas> playerBlcokList = new HashMap<>();

    public static void add(UUID uuid, Block block) {
        BlockDatas blockDatas = new BlockDatas(block);
        playerBlcokList.put(uuid, blockDatas);
    }
    public static BlockDatas get(UUID uuid) {
        return playerBlcokList.get(uuid);
    }
    public static boolean contains(UUID uuid) {
        return playerBlcokList.containsKey(uuid);
    }



    public static void remove(UUID uuid) {
        playerBlcokList.remove(uuid);
    }
}
