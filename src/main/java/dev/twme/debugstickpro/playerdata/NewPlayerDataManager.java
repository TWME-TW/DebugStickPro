package dev.twme.debugstickpro.playerdata;

import dev.twme.debugstickpro.display.ActionbarUtil;
import dev.twme.debugstickpro.mode.classic.ClassicUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class NewPlayerDataManager {

    // 儲存玩家的資訊
    private static final HashMap<UUID, NewPlayerData> playerDataMap = new HashMap<>();

    // 該玩家是否加入顯示列表
    private static final HashSet<UUID> playerEnableDisplaySet = new HashSet<>();

    public static void setPlayerData(UUID uuid, NewPlayerData playerData){
        playerDataMap.put(uuid, playerData);
    }

    public static NewPlayerData getPlayerData(UUID uuid){
        return playerDataMap.get(uuid);
    }

    public static void removePlayerData(UUID uuid){
        playerDataMap.remove(uuid);
    }

    public static void addPlayerToDisplayList(UUID uuid){
        playerEnableDisplaySet.add(uuid);
    }

    public static void removePlayerFromDisplayList(UUID uuid){
        playerEnableDisplaySet.remove(uuid);
        ActionbarUtil.removeActionBar(uuid);
    }

    public boolean isPlayerInDisplayList(UUID uuid){
        return playerEnableDisplaySet.contains(uuid);
    }

    public static HashSet<UUID> getDisplaySet(){
        return playerEnableDisplaySet;
    }

    public static void nextDebugStickMode(UUID uuid){
        NewPlayerData playerData = getPlayerData(uuid);
        Player player = Bukkit.getPlayer(uuid);
        switch (playerData.getDebugStickMode()) {
            case Classic:
                if (player.hasPermission("debugstickpro.mode.copy")) {
                    playerData.setDebugStickMode(DebugStickMode.Copy);
                }
                break;
            case Copy:
                if (player.hasPermission("debugstickpro.mode.freeze")) {
                    playerData.setDebugStickMode(DebugStickMode.Freeze);
                }
                playerData.setDebugStickMode(DebugStickMode.Freeze);
                break;
            case Freeze:
            default:
                playerData.setDebugStickMode(DebugStickMode.Classic);
                break;
        }
    }

    public static void playerLeftClick(UUID uuid){

        NewPlayerData playerData = getPlayerData(uuid);
        Player player = Bukkit.getPlayer(uuid);

        switch (playerData.getDebugStickMode()) {
            case Classic:
                ClassicUtil.changeSelectedSubBlockType(uuid, playerData);
                break;
            case Copy:

                break;
            case Freeze:

                break;
        }
    }



}
