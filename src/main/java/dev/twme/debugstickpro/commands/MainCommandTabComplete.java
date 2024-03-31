package dev.twme.debugstickpro.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MainCommandTabComplete implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) {
            return null;
        }
        ArrayList<String> list = new ArrayList<>();
        Player player = (Player) commandSender;
        if (args.length == 1) {
            if (player.hasPermission("debugstickpro.help")) {
                list.add("help");
            }
            if (player.hasPermission("debugstickpro.reload")) {
                list.add("reload");
            }
            if (player.hasPermission("debugstickpro.give")) {
                list.add("give");
            }
            if (player.hasPermission("debugstickpro.mode")) {
                list.add("mode");
            }
            return list;
        }

        if (args.length == 2) {

            if ("give".equalsIgnoreCase(args[0])) {
                return null;
            }

            if (args[0].equalsIgnoreCase("mode")) {
                if (player.hasPermission("debugstickpro.mode")) {
                    list.add("classic");
                }
                if (player.hasPermission("debugstickpro.mode.copy")) {
                    list.add("copy");
                }
                if (player.hasPermission("debugstickpro.mode.freeze")) {
                    list.add("freeze");
                }
                return list;
            }
        }
        return list;
    }
}
