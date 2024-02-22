package dev.twme.debugstickpro.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DebugStickItemCheck {
    public static boolean isDebugStickItem(ItemStack item) {
        if (item == null ) {
            return false;
        }
        if (item.getType() != Material.BLAZE_ROD) {
            return false;
        }
        if (item.getItemMeta() == null) {
            return false;
        }
        if (!item.getItemMeta().getPersistentDataContainer().has(PersistentKeys.DEBUG_STICK_ITEM)){
            return false;
        }
        return true;
    }
    public static boolean checkPlayer(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null) {
            return false;
        }
        return isDebugStickItem(item);
    }
}
