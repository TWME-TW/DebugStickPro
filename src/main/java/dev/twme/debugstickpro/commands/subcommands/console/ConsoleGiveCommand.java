package dev.twme.debugstickpro.commands.subcommands.console;

import dev.twme.debugstickpro.localization.I18n;
import dev.twme.debugstickpro.localization.Lang;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import dev.twme.debugstickpro.utils.DebugStickItem;
import dev.twme.debugstickpro.utils.TextUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConsoleGiveCommand {
    public static boolean onGiveCommand(CommandSender sender, String[] args) {
        MiniMessage mm = MiniMessage.miniMessage();

        if (!sender.hasPermission("debugstickpro.give")) {
            Component parsed = mm.deserialize(I18n.string(Lang.CommandsMessages.NoPermission));
            TextUtil.send(sender, parsed);
            return true;
        }

        // Directly give the player a debug stick
        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                Component parsed = mm.deserialize(I18n.string(Lang.CommandsMessages.YouAreNotPlayer));
                TextUtil.send(sender, parsed);
                return true;
            }
            return true;
        }

        if (args.length != 2) {
            return false;
        }

        Player onlinePlayer = Bukkit.getPlayerExact(args[1]);

        // Check if player not exist
        if (onlinePlayer == null) {
            Component parsed = mm.deserialize(I18n.string(Lang.CommandsMessages.Give.NoPlayer));
            TextUtil.send(sender, parsed);
            return true;
        }

        // Give another player a debug stick
        onlinePlayer.getInventory().addItem(DebugStickItem.getDebugStickItem());
        Component parsed = mm.deserialize(I18n.string(Lang.CommandsMessages.Give.Success).replace("%player%", onlinePlayer.getName()));
        TextUtil.send(sender, parsed);

        if (DebugStickItem.checkPlayer(onlinePlayer)) {
            PlayerDataManager.addPlayerToDisplayList(onlinePlayer.getUniqueId());
        }

        return true;
    }
}
