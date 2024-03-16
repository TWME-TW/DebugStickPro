package dev.twme.debugstickpro.util;

import dev.twme.debugstickpro.configs.ConfigFile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class DebugStickItem {
    public static boolean isDebugStickItem(ItemStack item) {
        if (item == null ) {
            return false;
        }
        if (item.getType() != ConfigFile.DebugStickItem.Material) {
            return false;
        }
        if (item.getItemMeta() == null) {
            return false;
        }
        if (!item.getItemMeta().getPersistentDataContainer().has(PersistentKeys.DEBUG_STICK_ITEM)){
            return false;
        }
        if (ConfigFile.DebugStickItem.CustomModelData.Enabled) {
            if (item.getItemMeta().getCustomModelData() != ConfigFile.DebugStickItem.CustomModelData.CustomModelData) {
                return false;
            }
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

    public static ItemStack getDebugStickItem() {
        ItemStack itemStack = new ItemStack(ConfigFile.DebugStickItem.Material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        MiniMessage mm = MiniMessage.miniMessage();
        Component displayName = mm.deserialize(ConfigFile.DebugStickItem.DisplayName);
        itemMeta.displayName(displayName);
        itemMeta.lore(ConfigFile.DebugStickItem.Lore);
        itemMeta.getPersistentDataContainer().set(PersistentKeys.DEBUG_STICK_ITEM, PersistentDataType.STRING, "debugstickpro");
        if (ConfigFile.DebugStickItem.CustomModelData.Enabled) {
            itemMeta.setCustomModelData(ConfigFile.DebugStickItem.CustomModelData.CustomModelData);
        }
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}