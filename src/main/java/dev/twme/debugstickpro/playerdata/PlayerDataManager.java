package dev.twme.debugstickpro.playerdata;

import dev.twme.debugstickpro.display.ActionbarUtil;
import dev.twme.debugstickpro.events.PlayerChangeDebugStickModeEvent;
import dev.twme.debugstickpro.mode.classic.ClassicLeftClick;
import dev.twme.debugstickpro.mode.classic.ClassicRightClick;
import dev.twme.debugstickpro.mode.copy.CopyLeftClick;
import dev.twme.debugstickpro.mode.copy.CopyRightClick;
import dev.twme.debugstickpro.mode.freeze.FreezeBlockManager;
import dev.twme.debugstickpro.mode.freeze.FreezeLeftClick;
import dev.twme.debugstickpro.mode.freeze.FreezeRightClick;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class PlayerDataManager {

    // 儲存玩家的資訊
    private static final HashMap<UUID, PlayerData> playerDataMap = new HashMap<>();

    // 該玩家是否加入顯示列表
    private static final HashSet<UUID> playerEnableDisplaySet = new HashSet<>();

    public static void setPlayerData(UUID uuid, PlayerData playerData){
        playerDataMap.put(uuid, playerData);
    }

    public static PlayerData getPlayerData(UUID uuid){
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

    public static HashSet<UUID> getDisplaySet(){
        return playerEnableDisplaySet;
    }

    public static void nextDebugStickMode(UUID uuid){

        Player player = Bukkit.getPlayer(uuid);

        if (player.isSneaking()) {
            previousDebugStickMode(uuid);
            return;
        }

        PlayerData playerData = getPlayerData(uuid);

        ActionbarUtil.removeActionBar(uuid);
        switch (playerData.getDebugStickMode()) {
            case CLASSIC:
                if (player.hasPermission("debugstickpro.mode.copy")) {
                    if (modeChangeEventCancelled(uuid, DebugStickMode.CLASSIC, DebugStickMode.COPY)) {
                        return;
                    }
                    playerData.setDebugStickMode(DebugStickMode.COPY);
                    break;
                }
            case COPY:
                if (player.hasPermission("debugstickpro.mode.freeze")) {
                    if (modeChangeEventCancelled(uuid, DebugStickMode.COPY, DebugStickMode.FREEZE)) {
                        return;
                    }
                    playerData.setDebugStickMode(DebugStickMode.FREEZE);
                    break;
                }
            case FREEZE:
                if (modeChangeEventCancelled(uuid, DebugStickMode.FREEZE, DebugStickMode.CLASSIC)) {
                    return;
                }
            default:
                playerData.setDebugStickMode(DebugStickMode.CLASSIC);
                break;
        }
    }

    public static void previousDebugStickMode(UUID uuid){
        Player player = Bukkit.getPlayer(uuid);
        PlayerData playerData = getPlayerData(uuid);

        ActionbarUtil.removeActionBar(uuid);
        switch (playerData.getDebugStickMode()) {
            case CLASSIC:
                if (player.hasPermission("debugstickpro.mode.freeze")) {
                    if (modeChangeEventCancelled(uuid, DebugStickMode.CLASSIC, DebugStickMode.FREEZE)) {
                        return;
                    }
                    playerData.setDebugStickMode(DebugStickMode.FREEZE);
                    break;
                }
            case FREEZE:
                if (player.hasPermission("debugstickpro.mode.copy")) {
                    if (modeChangeEventCancelled(uuid, DebugStickMode.FREEZE, DebugStickMode.COPY)) {
                        return;
                    }
                    playerData.setDebugStickMode(DebugStickMode.COPY);
                    break;
                }
            case COPY:
                if (modeChangeEventCancelled(uuid, DebugStickMode.COPY, DebugStickMode.CLASSIC)) {
                    return;
                }
            default:
                playerData.setDebugStickMode(DebugStickMode.CLASSIC);
                break;
        }
    }

    public static boolean modeChangeEventCancelled(UUID playerUUID, DebugStickMode previousMode, DebugStickMode newMode) {
        PlayerChangeDebugStickModeEvent event = new PlayerChangeDebugStickModeEvent(playerUUID, previousMode, newMode);
        Bukkit.getPluginManager().callEvent(event);
        return event.isCancelled();
    }

    public static void playerLeftClick(UUID uuid){

        PlayerData playerData = getPlayerData(uuid);

        switch (playerData.getDebugStickMode()) {
            case CLASSIC:
                ClassicLeftClick.changeSelectedSubBlockType(uuid, playerData);
                break;
            case COPY:
                CopyLeftClick.onLeftClick(uuid, playerData);
                break;
            case FREEZE:
                FreezeLeftClick.onLeftClick(uuid);
                break;
        }
    }

    public static void playerRightClick(UUID uuid){

        PlayerData playerData = getPlayerData(uuid);

        switch (playerData.getDebugStickMode()) {
            case CLASSIC:
                ClassicRightClick.changeSelectedSubBlockDataValue(uuid, playerData);
                break;
            case COPY:
                CopyRightClick.onRightClick(uuid, playerData);
                break;
            case FREEZE:
                FreezeRightClick.onRightClick(uuid);
                break;
        }
    }
}
