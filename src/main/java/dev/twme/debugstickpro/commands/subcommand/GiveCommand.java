package dev.twme.debugstickpro.commands.subcommand;

import dev.twme.debugstickpro.configs.LangFile;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import dev.twme.debugstickpro.util.DebugStickItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GiveCommand {
    public static boolean onGiveCommand(Player player, String[] args) {
        MiniMessage mm = MiniMessage.miniMessage();

        if (!player.hasPermission("debugstickpro.give")) {
            Component parsed = mm.deserialize(LangFile.CommandsMessages.NoPermission);
            player.sendMessage(parsed);
            return true;
        }

        if (args.length == 1) {
            player.getInventory().addItem(DebugStickItem.getDebugStickItem());

            Component parsed = mm.deserialize(LangFile.CommandsMessages.Give.Success.replace("%player%", player.getName()));
            player.sendMessage(parsed);
            if (DebugStickItem.checkPlayer(player)) {
                PlayerDataManager.addPlayerToDisplayList(player.getUniqueId());
            }
            return true;
        } else {
            Player onlinePlayer = Bukkit.getPlayerExact(args[1]);
            if (onlinePlayer == null) {
                Component parsed = mm.deserialize(LangFile.CommandsMessages.Give.NoPlayer);
                player.sendMessage(parsed);
                return true;
            } else {
                onlinePlayer.getInventory().addItem(DebugStickItem.getDebugStickItem());
                Component parsed = mm.deserialize(LangFile.CommandsMessages.Give.Success.replace("%player%", player.getName()));
                player.sendMessage(parsed);
                if (DebugStickItem.checkPlayer(onlinePlayer)) {
                    PlayerDataManager.addPlayerToDisplayList(onlinePlayer.getUniqueId());
                }
                return true;
            }
        }
    }
}
