package dev.twme.debugstickpro.util.player;

import dev.twme.debugstickpro.util.player.playerdata.util.PlayerData;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDisplayUtil {
    public static HashMap<UUID, PlayerData> playerDataList = new HashMap<>();
    public static void add(UUID uuid){
        if (!playerDataList.containsKey(uuid)) {
            playerDataList.put(uuid, new PlayerData());
        }
    }
}
