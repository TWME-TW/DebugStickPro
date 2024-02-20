package dev.twme.debugstickpro.util.player.playerdata;

import dev.twme.debugstickpro.util.actionbar.ActionbarUtil;
import dev.twme.debugstickpro.util.player.playerdata.util.DebugStickMode;
import dev.twme.debugstickpro.util.player.playerdata.util.PlayerData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class PlayerDataManager {
    private static HashMap<UUID, PlayerData> playerData = new HashMap<>();
    private static ArrayList<UUID> playerEnableDisplay = new ArrayList<>();

    public static void setPlayerData(UUID uuid, PlayerData playerData){
        PlayerDataManager.playerData.put(uuid, playerData);
    }
    public static PlayerData getPlayerData(UUID uuid){
        if (playerData.containsKey(uuid)){
            return playerData.get(uuid);
        } else {
            PlayerData newPlayerData = new PlayerData(uuid);
            playerData.put(uuid, newPlayerData);
            return newPlayerData;
        }
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
    public static ArrayList<UUID> getPlayerEnableDisplay(){
        return playerEnableDisplay;
    }
}
