package dev.twme.debugstickpro.utils;

import dev.twme.debugstickpro.config.ConfigFile;
import dev.twme.debugstickpro.playerdata.DebugStickMode;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

/**
 * CustomModelDataManager
 */
public class CustomModelDataManager {
    /**
     * updateItem
     * @param player Player
     * @param newMode DebugStickMode new mode
     * @return boolean true: success, false: failed
     */
    public static boolean updateItem(Player player, DebugStickMode newMode) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if(DebugStickItem.isDebugStickItem(item)) {
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setCustomModelData(getCustomModelData(newMode));
            itemMeta.getPersistentDataContainer().set(PersistentKeys.DEBUG_STICK_MODE, PersistentDataType.STRING, newMode.name());
            item.setItemMeta(itemMeta);
            player.getInventory().setItemInMainHand(item);
            player.updateInventory();
            return true;
        }
        return false;
    }

    /**
     * updatePlayerMode
     * @param player Player
     * @return boolean true: success, false: failed
     */
    public static boolean updatePlayerMode(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        return updatePlayerMode(player, item);
    }

    /**
     * updatePlayerMode
     * @param player Player
     * @param item ItemStack
     * @return boolean true: success, false: failed
     */
    public static boolean updatePlayerMode(Player player, ItemStack item) {
        if (!DebugStickItem.isDebugStickItem(item)) {
            return false;
        }

        if (!item.getItemMeta().getPersistentDataContainer().has(PersistentKeys.DEBUG_STICK_MODE, PersistentDataType.STRING)) {
            return false;
        }

        DebugStickMode mode = DebugStickMode.valueOf(item.getItemMeta().getPersistentDataContainer().get(PersistentKeys.DEBUG_STICK_MODE, PersistentDataType.STRING));
        PlayerDataManager.getPlayerData(player.getUniqueId()).setDebugStickMode(mode);
        return true;
    }

    /**
     * getCustomModelData
     * @param mode DebugStickMode
     * @return int
     */
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
