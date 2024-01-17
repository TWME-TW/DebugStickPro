package dev.twme.debugstickpro.commmands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MainCommands implements CommandExecutor , TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player)){
            return false;
        }
        Player player = (Player) commandSender;

        if (strings.length == 0){
            player.sendMessage("You need to specify a subcommand!");
            return  true;
        }

        if (strings[0].equalsIgnoreCase("help")){
            player.sendMessage("Help command");
            return true;
        }
        if (strings[0].equalsIgnoreCase("reload")){
            player.sendMessage("Reload command");
            return true;
        }
        if (strings[0].equalsIgnoreCase("give")){
            player.sendMessage("Give command");
            return true;
        }

        if (player.hasPermission("debugstickpro.use")){
            player.sendMessage("You have permission to use this command!");
        } else {
            player.sendMessage("You do not have permission to use this command!");
        }
        

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player)){
            return null;
        }
        ArrayList<String> list = new ArrayList<>();
        Player player = (Player) commandSender;
        if (player.hasPermission("debugstickpro.help")){
            list.add("help");
        }
        if (player.hasPermission("debugstickpro.reload")){
            list.add("reload");
        }
        if (player.hasPermission("debugstickpro.give")){
            list.add("give");
        }
        return null;
    }
}
