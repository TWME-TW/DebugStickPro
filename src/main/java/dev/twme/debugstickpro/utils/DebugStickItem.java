package dev.twme.debugstickpro.utils;

import dev.twme.debugstickpro.config.ConfigFile;
import dev.twme.debugstickpro.playerdata.DebugStickMode;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public final class DebugStickItem {
    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    public static boolean isDebugStickItem(ItemStack item) {
        if (item == null) {
            return false;
        }
        if (item.getType() != ConfigFile.DebugStickItem.Material) {
            return false;
        }
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) {
            return false;
        }
        if (!itemMeta.getPersistentDataContainer().has(PersistentKeys.DEBUG_STICK_ITEM)) {
            return false;
        }
        return true;
    }

    public static boolean checkPlayer(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        return isDebugStickItem(item);
    }

    public static ItemStack getDebugStickItem() {
        ItemStack itemStack = new ItemStack(ConfigFile.DebugStickItem.Material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        Component displayName = MINI_MESSAGE.deserialize(ConfigFile.DebugStickItem.DisplayName);
        itemMeta.displayName(displayName);
        itemMeta.lore(ConfigFile.DebugStickItem.Lore);
        itemMeta.getPersistentDataContainer().set(PersistentKeys.DEBUG_STICK_ITEM, PersistentDataType.STRING, "debugstickpro");
        if (ConfigFile.DebugStickItem.CustomModelData.Enabled) {
            itemMeta.setCustomModelData(ConfigFile.DebugStickItem.CustomModelData.ClassicMode);
            itemMeta.getPersistentDataContainer().set(PersistentKeys.DEBUG_STICK_MODE, PersistentDataType.STRING, DebugStickMode.CLASSIC.name());
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
