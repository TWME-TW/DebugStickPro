package dev.twme.debugstickpro.utils;

import dev.twme.debugstickpro.config.ConfigFile;
import dev.twme.debugstickpro.playerdata.DebugStickMode;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class CustomModelDataManager {
    public static void updateItem(Player player, DebugStickMode newMode) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if(DebugStickItem.isDebugStickItem(item)) {
            item.getItemMeta().setCustomModelData(getCustomModelData(newMode));
            item.getItemMeta().getPersistentDataContainer().set(PersistentKeys.DEBUG_STICK_MODE, PersistentDataType.STRING, newMode.name());
        }
    }


    public static void updatePlayerMode(Player player) {
        if (!DebugStickItem.checkPlayer(player)) {
            return;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        DebugStickMode mode = DebugStickMode.valueOf(item.getItemMeta().getPersistentDataContainer().get(PersistentKeys.DEBUG_STICK_MODE, PersistentDataType.STRING));
        PlayerDataManager.getPlayerData(player.getUniqueId()).setDebugStickMode(mode);
    }

    private static int getCustomModelData(DebugStickMode mode) {

        if (mode == DebugStickMode.COPY) {
            return ConfigFile.DebugStickItem.CustomModelData.CopyMode;
        }
        if (mode == DebugStickMode.FREEZE) {
            return ConfigFile.DebugStickItem.CustomModelData.FreezeMode;
        }
        return ConfigFile.DebugStickItem.CustomModelData.ClassicMode;
    }
}
