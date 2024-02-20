package dev.twme.debugstickpro.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DebugStickItemCheck {
    public static boolean isDebugStickItem(ItemStack item) {
        if (item == null || item.getType() != Material.BLAZE_ROD) {
            return false;
        }
        if (item.getItemMeta() == null) {
            return false;
        }
        return item.getItemMeta().getPersistentDataContainer().has(PersistentKey.DEBUG_STICK_ITEM);
    }
    public static boolean checkPlayer(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null) {
            return false;
        }
        return isDebugStickItem(item);
    }
}
