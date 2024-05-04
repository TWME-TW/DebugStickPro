package dev.twme.debugstickpro.commands.subcommands.console;

import dev.twme.debugstickpro.localization.I18n;
import dev.twme.debugstickpro.localization.Lang;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;

public class ConsoleHelpCommand {
    public static boolean onHelpCommand(CommandSender sender, String[] args) {
        MiniMessage mm = MiniMessage.miniMessage();

        if (args.length > 1) {
            return false;
        }
        if (!sender.hasPermission("debugstickpro.help")) {
            Component parsed = mm.deserialize(I18n.string(Lang.CommandsMessages.NoPermission));
            sender.sendMessage(parsed);
            return true;
        }
        for (String message : I18n.list(Lang.CommandsMessages.Help.HelpMessage)) {
            sender.sendMessage(mm.deserialize(message));
        }
        return true;
    }
}
