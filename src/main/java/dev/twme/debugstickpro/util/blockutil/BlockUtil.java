package dev.twme.debugstickpro.util.blockutil;

import org.bukkit.block.data.BlockData;

import java.util.*;

public class BlockUtil {
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

    public static void remove(UUID uuid) {
        playerBlcokList.remove(uuid);
    }
}
