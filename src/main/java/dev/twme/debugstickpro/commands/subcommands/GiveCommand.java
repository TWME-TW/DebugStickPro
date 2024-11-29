package dev.twme.debugstickpro.commands.subcommands;

import dev.twme.debugstickpro.localization.I18n;
import dev.twme.debugstickpro.localization.Lang;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import dev.twme.debugstickpro.utils.CustomModelDataManager;
import dev.twme.debugstickpro.utils.DebugStickItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GiveCommand {
    public static boolean onGiveCommand(Player player, String[] args) {
        MiniMessage mm = MiniMessage.miniMessage();
        UUID playerUUID = player.getUniqueId();
        if (!player.hasPermission("debugstickpro.give")) {
            Component parsed = mm.deserialize(I18n.string(playerUUID, Lang.CommandsMessages.NoPermission));
            player.sendMessage(parsed);
            return true;
        }

        // Directly give the player a debug stick
        if (args.length == 1) {
            player.getInventory().addItem(DebugStickItem.getDebugStickItem());

            Component parsed = mm.deserialize(I18n.string(playerUUID, Lang.CommandsMessages.Give.Success).replace("%player%", player.getName()));
            player.sendMessage(parsed);
            if (DebugStickItem.checkPlayer(player)) {
                PlayerDataManager.addPlayerToDisplayList(player.getUniqueId());
            }
            return true;
        }

        if (args.length != 2) {
            return false;
        }

        Player onlinePlayer = Bukkit.getPlayerExact(args[1]);

        // Check if player not exist
        if (onlinePlayer == null) {
            Component parsed = mm.deserialize(I18n.string(playerUUID, Lang.CommandsMessages.Give.NoPlayer));
            player.sendMessage(parsed);
            return true;
        }

        // Give another player a debug stick
        onlinePlayer.getInventory().addItem(DebugStickItem.getDebugStickItem());
        CustomModelDataManager.updatePlayerMode(onlinePlayer);
        Component parsed = mm.deserialize(I18n.string(playerUUID, Lang.CommandsMessages.Give.Success).replace("%player%", onlinePlayer.getName()));
        player.sendMessage(parsed);

        if (DebugStickItem.checkPlayer(onlinePlayer)) {
            PlayerDataManager.addPlayerToDisplayList(onlinePlayer.getUniqueId());
        }

        return true;
    }
}
