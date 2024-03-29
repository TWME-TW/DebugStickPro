package dev.twme.debugstickpro.commands.subcommand;

import dev.twme.debugstickpro.events.PlayerChangeDebugStickModeEvent;
import dev.twme.debugstickpro.localization.I18n;
import dev.twme.debugstickpro.localization.Lang;
import dev.twme.debugstickpro.playerdata.DebugStickMode;
import dev.twme.debugstickpro.playerdata.PlayerData;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
            player.sendMessage(parsed);
            return true;
        }
        if (args.length == 1) {
            Component parsed = mm.deserialize(I18n.string(playerUUID, Lang.CommandsMessages.Mode.Usage));
            player.sendMessage(parsed);
            return true;
        } else {
            if (args[1].equalsIgnoreCase("classic")) {
                PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());

                if (modeChangeEventCancelled(player.getUniqueId(), playerData.getDebugStickMode(), DebugStickMode.CLASSIC)) {
                    return true;
                }

                PlayerDataManager.setPlayerData(player.getUniqueId(), playerData.setDebugStickMode(DebugStickMode.CLASSIC));
                Component parsed = mm.deserialize(I18n.string(playerUUID, Lang.CommandsMessages.Mode.SuccessSetToClassic));
                player.sendMessage(parsed);
                return true;
            } else if (args[1].equalsIgnoreCase("copy")) {
                if (player.hasPermission("debugstickpro.mode.copy")) {
                    PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());

                    if (modeChangeEventCancelled(player.getUniqueId(), playerData.getDebugStickMode(), DebugStickMode.COPY)) {
                        return true;
                    }

                    PlayerDataManager.setPlayerData(player.getUniqueId(), playerData.setDebugStickMode(DebugStickMode.COPY));
                    Component parsed = mm.deserialize(I18n.string(playerUUID, Lang.CommandsMessages.Mode.SuccessSetToCopy));
                    player.sendMessage(parsed);
                    return true;
                } else {
                    Component parsed = mm.deserialize(I18n.string(playerUUID, Lang.CommandsMessages.NoPermission));
                    player.sendMessage(parsed);
                    return true;
                }
            } else if (args[1].equalsIgnoreCase("freeze")) {
                if (player.hasPermission("debugstickpro.mode.freeze")) {
                    PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());

                    if (modeChangeEventCancelled(player.getUniqueId(), playerData.getDebugStickMode(), DebugStickMode.FREEZE)) {
                        return true;
                    }

                    PlayerDataManager.setPlayerData(player.getUniqueId(), PlayerDataManager.getPlayerData(player.getUniqueId()).setDebugStickMode(DebugStickMode.FREEZE));
                    Component parsed = mm.deserialize(I18n.string(playerUUID, Lang.CommandsMessages.Mode.SuccessSetToFreeze));
                    player.sendMessage(parsed);
                    return true;
                } else {
                    Component parsed = mm.deserialize(I18n.string(playerUUID, Lang.CommandsMessages.NoPermission));
                    player.sendMessage(parsed);
                    return true;
                }
            } else {
                Component parsed = mm.deserialize(I18n.string(playerUUID, Lang.CommandsMessages.Mode.Usage));
                player.sendMessage(parsed);
                return true;
            }
        }
    }
}
