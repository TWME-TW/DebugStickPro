package dev.twme.debugstickpro.commands;

import dev.twme.debugstickpro.commands.subcommand.GiveCommand;
import dev.twme.debugstickpro.commands.subcommand.HelpCommand;
import dev.twme.debugstickpro.commands.subcommand.ModeCommand;
import dev.twme.debugstickpro.commands.subcommand.ReloadCommand;
import dev.twme.debugstickpro.configs.LangFile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MainCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(commandSender instanceof Player)) {
            MiniMessage mm = MiniMessage.miniMessage();
            Component parsed = mm.deserialize(LangFile.CommandsMessages.YouAreNotPlayer);
            commandSender.sendMessage(parsed);
            return true;
        }

        Player player = (Player) commandSender;

        if (strings.length == 0 || strings[0].equalsIgnoreCase("help")) {
            return HelpCommand.onHelpCommand(player, strings);
        }
        if (strings[0].equalsIgnoreCase("reload")) {
            return ReloadCommand.onReloadCommand(player, strings);
        }
        if (strings[0].equalsIgnoreCase("give")) {
            return GiveCommand.onGiveCommand(player, strings);
        }

        if (strings[0].equalsIgnoreCase("mode")) {
            return ModeCommand.onModeCommand(player, strings);
        }
        return false;
    }
}
