package dev.twme.debugstickpro.commands.subcommands.console;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.localization.I18n;
import dev.twme.debugstickpro.localization.Lang;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;

public class ConsoleReloadCommand {
    public static boolean onReloadCommand(CommandSender sender, String[] args) {
        MiniMessage mm = MiniMessage.miniMessage();

        if (args.length > 1) {
            return false;
        }
        if (!sender.hasPermission("debugstickpro.reload")) {
            Component parsed = mm.deserialize(I18n.string(Lang.CommandsMessages.NoPermission));
            sender.sendMessage(parsed);
            return true;
        }
        DebugStickPro.getInstance().onReload();
        Component parsed = mm.deserialize(I18n.string(Lang.CommandsMessages.Reload.Success));
        sender.sendMessage(parsed);
        return true;
    }
}
