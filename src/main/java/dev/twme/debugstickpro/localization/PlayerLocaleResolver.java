package dev.twme.debugstickpro.localization;

import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

public final class PlayerLocaleResolver {
    private PlayerLocaleResolver() {
    }

    public static String resolve(Player player) {
        try {
            Method localeMethod = Player.class.getMethod("locale");
            Object locale = localeMethod.invoke(player);
            if (locale instanceof Locale paperLocale) {
                return paperLocale.toString();
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
            // Spigot does not expose Paper's Locale-returning API.
        }

        return player.getLocale();
    }
}
