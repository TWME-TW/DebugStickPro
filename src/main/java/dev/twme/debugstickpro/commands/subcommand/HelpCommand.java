package dev.twme.debugstickpro.commands.subcommand;

import dev.twme.debugstickpro.configs.LangFile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

public class HelpCommand {
    public static boolean onHelpCommand(Player player, String[] args) {
        MiniMessage mm = MiniMessage.miniMessage();
        if (args.length > 1) {
            return false;
        }
        if (!player.hasPermission("debugstickpro.help")) {
            Component parsed = mm.deserialize(LangFile.CommandsMessages.NoPermission);
            player.sendMessage(parsed);
            return true;
        }
        for (Component component : LangFile.CommandsMessages.Help.HelpMessage) {
            player.sendMessage(component);
        }
        return true;
    }
}
