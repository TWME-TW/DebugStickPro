package dev.twme.debugstickpro.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.audience.Audience;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class TextUtil {
    private static final LegacyComponentSerializer LEGACY = LegacyComponentSerializer.legacySection();

    private TextUtil() {
    }

    public static void send(CommandSender sender, Component component) {
        if (Audience.class.isInstance(sender)) {
            Audience audience = Audience.class.cast(sender);
            audience.sendMessage(component);
            return;
        }
        sender.sendMessage(LEGACY.serialize(component));
    }

    public static void sendActionBar(Player player, Component component) {
        if (Audience.class.isInstance(player)) {
            Audience audience = Audience.class.cast(player);
            audience.sendActionBar(component);
            return;
        }
        player.spigot().sendMessage(
                net.md_5.bungee.api.ChatMessageType.ACTION_BAR,
                net.md_5.bungee.api.chat.TextComponent.fromLegacyText(LEGACY.serialize(component))
        );
    }

    public static void setDisplayName(ItemMeta itemMeta, Component component) {
        if (!invokePaperItemMeta(itemMeta, "displayName", Component.class, component)) {
            itemMeta.setDisplayName(LEGACY.serialize(component));
        }
    }

    public static void setLore(ItemMeta itemMeta, List<Component> lore) {
        if (!invokePaperItemMeta(itemMeta, "lore", List.class, lore)) {
            itemMeta.setLore(lore.stream().map(LEGACY::serialize).toList());
        }
    }

    private static boolean invokePaperItemMeta(ItemMeta itemMeta, String methodName, Class<?> parameterType, Object value) {
        try {
            ItemMeta.class.getMethod(methodName, parameterType).invoke(itemMeta, value);
            return true;
        } catch (NoSuchMethodException ignored) {
            return false;
        } catch (IllegalAccessException exception) {
            throw new IllegalStateException("Cannot access Paper ItemMeta method " + methodName, exception);
        } catch (InvocationTargetException exception) {
            throw new IllegalStateException("Paper ItemMeta method failed: " + methodName, exception.getCause());
        }
    }
}
