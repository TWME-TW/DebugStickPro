package dev.twme.debugstickpro.commmands;

import dev.twme.debugstickpro.util.actionbar.ActionbarUtil;
import dev.twme.debugstickpro.util.PersistentKey;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MainCommands implements CommandExecutor , TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player)){
            commandSender.sendMessage("You need to be a player to use this command!");
            return true;
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
            player.getInventory().addItem(getDebugStickItem());
            player.sendMessage("Give command");
            return true;
        }

        if (player.hasPermission("debugstickpro.use")){
            player.sendMessage("You have permission to use this command!");
        } else {
            player.sendMessage("You do not have permission to use this command!");
        }

        if (strings[0].equalsIgnoreCase("a")){
            ActionbarUtil.sendActionBar(player, "Text: " + strings[1]);
            return true;
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

    private static ItemStack getDebugStickItem(){
        ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Debug Stick Pro");
        itemMeta.getPersistentDataContainer().set(PersistentKey.DEBUG_STICK_ITEM, PersistentDataType.STRING, "debugstickpro");

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
