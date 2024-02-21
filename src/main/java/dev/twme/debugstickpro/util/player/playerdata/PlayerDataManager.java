package dev.twme.debugstickpro.util.player.playerdata;

import dev.twme.debugstickpro.util.actionbar.ActionbarUtil;

import java.util.*;

public class PlayerDataManager {
    private static HashMap<UUID, PlayerData> playerData = new HashMap<>();
    private static HashSet<UUID> playerEnableDisplay = new HashSet<>();

    public static void setPlayerData(UUID uuid, PlayerData playerData){
        PlayerDataManager.playerData.put(uuid, playerData);
    }
    public static PlayerData getPlayerData(UUID uuid){
        return playerData.get(uuid);
    }
    public static void removePlayerData(UUID uuid){
        playerData.remove(uuid);
    }

    public static Set<UUID> getPlayerDataUUIDs(){
        return playerData.keySet();
    }

    public static void addPlayerEnableDisplay(UUID uuid){
        playerEnableDisplay.add(uuid);
    }
    public static void removePlayerEnableDisplay(UUID uuid){
        //TODO: 這裡有可能會有問題
        ActionbarUtil.removeActionBar(uuid);
        playerEnableDisplay.remove(uuid);
    }
    public static HashSet<UUID> getPlayerEnableDisplay(){
        return playerEnableDisplay;
    }
}
