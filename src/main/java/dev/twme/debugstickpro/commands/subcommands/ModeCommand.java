package dev.twme.debugstickpro.commands.subcommands;

import dev.twme.debugstickpro.events.PlayerChangeDebugStickModeEvent;
import dev.twme.debugstickpro.localization.I18n;
import dev.twme.debugstickpro.localization.Lang;
import dev.twme.debugstickpro.playerdata.DebugStickMode;
import dev.twme.debugstickpro.playerdata.PlayerData;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import dev.twme.debugstickpro.utils.CustomModelDataManager;
import dev.twme.debugstickpro.utils.DebugStickItem;
import dev.twme.debugstickpro.utils.TextUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Locale;
import java.util.UUID;

public class ModeCommand {

    public static boolean modeChangeEventCancelled(UUID playerUUID, DebugStickMode previousMode, DebugStickMode newMode) {
        PlayerChangeDebugStickModeEvent event = new PlayerChangeDebugStickModeEvent(playerUUID, previousMode, newMode);

        Bukkit.getPluginManager().callEvent(event);

        return event.isCancelled();
    }

    public static boolean onModeCommand(Player player, String[] args) {
        MiniMessage mm = MiniMessage.miniMessage();
        UUID playerUUID = player.getUniqueId();
        if (!player.hasPermission("debugstickpro.mode")) {
            Component parsed = mm.deserialize(I18n.string(playerUUID, Lang.CommandsMessages.NoPermission));
            TextUtil.send(player, parsed);
            return true;
        }

        // If no mode is specified, display the usage message
        if (args.length == 1) {
            Component parsed = mm.deserialize(I18n.string(playerUUID, Lang.CommandsMessages.Mode.Usage));
            TextUtil.send(player, parsed);
            return true;
        }

        if (args.length != 2) {
            return false;
        }

        DebugStickMode newMode = parseMode(args[1]);

        if (newMode == null) {
            Component parsed = mm.deserialize(I18n.string(playerUUID, Lang.CommandsMessages.Mode.Usage));
            TextUtil.send(player, parsed);
            return true;
        }

        if (!hasModePermission(player, newMode)) {
            Component parsed = mm.deserialize(I18n.string(playerUUID, Lang.CommandsMessages.NoPermission));
            TextUtil.send(player, parsed);
            return true;
        }

        if (!DebugStickItem.checkPlayer(player)) {
            Component parsed = mm.deserialize(I18n.string(playerUUID, Lang.CommandsMessages.Mode.MustHoldDebugStick));
            TextUtil.send(player, parsed);
            return true;
        }

        PlayerData playerData = PlayerDataManager.getOrCreatePlayerData(player.getUniqueId());

        if (modeChangeEventCancelled(player.getUniqueId(), playerData.getDebugStickMode(), newMode)) {
            return true;
        }

        PlayerDataManager.setPlayerData(player.getUniqueId(), playerData.setDebugStickMode(newMode));

        CustomModelDataManager.updateItem(player, newMode);

        Component parsed = mm.deserialize(I18n.string(playerUUID, successMessageKey(newMode)));
        TextUtil.send(player, parsed);
        return true;
    }

    private static DebugStickMode parseMode(String mode) {
        return switch (mode.toLowerCase(Locale.ROOT)) {
            case "classic" -> DebugStickMode.CLASSIC;
            case "copy" -> DebugStickMode.COPY;
            case "freeze" -> DebugStickMode.FREEZE;
            default -> null;
        };
    }

    private static boolean hasModePermission(Player player, DebugStickMode mode) {
        return switch (mode) {
            case CLASSIC -> true;
            case COPY -> player.hasPermission("debugstickpro.mode.copy");
            case FREEZE -> player.hasPermission("debugstickpro.mode.freeze");
        };
    }

    private static String successMessageKey(DebugStickMode mode) {
        return switch (mode) {
            case CLASSIC -> Lang.CommandsMessages.Mode.SuccessSetToClassic;
            case COPY -> Lang.CommandsMessages.Mode.SuccessSetToCopy;
            case FREEZE -> Lang.CommandsMessages.Mode.SuccessSetToFreeze;
        };
    }
}
