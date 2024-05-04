package dev.twme.debugstickpro.commands;

import dev.twme.debugstickpro.commands.subcommands.GiveCommand;
import dev.twme.debugstickpro.commands.subcommands.HelpCommand;
import dev.twme.debugstickpro.commands.subcommands.ModeCommand;
import dev.twme.debugstickpro.commands.subcommands.ReloadCommand;
import dev.twme.debugstickpro.commands.subcommands.console.ConsoleGiveCommand;
import dev.twme.debugstickpro.commands.subcommands.console.ConsoleHelpCommand;
import dev.twme.debugstickpro.commands.subcommands.console.ConsoleReloadCommand;
import dev.twme.debugstickpro.localization.I18n;
import dev.twme.debugstickpro.localization.Lang;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!(commandSender instanceof Player)) {
            return switch (args[0].toLowerCase()) {
                case "help" -> ConsoleHelpCommand.onHelpCommand(commandSender, args);
                case "give" -> ConsoleGiveCommand.onGiveCommand(commandSender, args);
                case "reload" -> ConsoleReloadCommand.onReloadCommand(commandSender, args);
                default -> false;
            };
        }

        Player player = (Player) commandSender;

        if (args.length == 0) {
            return HelpCommand.onHelpCommand(player, args);
        }

        return switch (args[0].toLowerCase()) {
            case "help" -> HelpCommand.onHelpCommand(player, args);
            case "reload" -> ReloadCommand.onReloadCommand(player, args);
            case "give" -> GiveCommand.onGiveCommand(player, args);
            case "mode" -> ModeCommand.onModeCommand(player, args);
            default -> false;
        };

    }
}
