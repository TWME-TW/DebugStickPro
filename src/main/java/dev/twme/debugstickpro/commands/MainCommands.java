package dev.twme.debugstickpro.commands;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.configs.ConfigFile;
import dev.twme.debugstickpro.configs.LangFile;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import dev.twme.debugstickpro.util.DebugStickItemCheck;
import dev.twme.debugstickpro.display.ActionbarUtil;
import dev.twme.debugstickpro.util.PersistentKeys;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
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

        MiniMessage mm = MiniMessage.miniMessage();

        if (!(commandSender instanceof Player)){
            Component parsed = mm.deserialize(LangFile.CommandsMessages.YouAreNotPlayer);
            commandSender.sendMessage(parsed);
            return true;
        }

        Player player = (Player) commandSender;

        if (strings.length == 0 || strings[0].equalsIgnoreCase("help")){
            Component parsed = mm.deserialize(LangFile.CommandsMessages.Help.Title);
            player.sendMessage(parsed);
            Component component = mm.deserialize(LangFile.CommandsMessages.Help.Usage);
            player.sendMessage(component);
            Component component1 = mm.deserialize(LangFile.CommandsMessages.Help.Description);
            player.sendMessage(component1);
            return true;
        }
        if (strings[0].equalsIgnoreCase("reload")){
            DebugStickPro.getInstance().reload();
            Component parsed = mm.deserialize(LangFile.CommandsMessages.Reload.Success);
            player.sendMessage(parsed);
            return true;
        }
        if (strings[0].equalsIgnoreCase("give")){
            if (strings.length == 1) {
                player.getInventory().addItem(getDebugStickItem());

                Component parsed = mm.deserialize(LangFile.CommandsMessages.Give.Success.replace("%player%", player.getName()));
                player.sendMessage(parsed);
                if (DebugStickItemCheck.checkPlayer(player)) {
                    PlayerDataManager.addPlayerToDisplayList(player.getUniqueId());
                }
                return true;
            } else {
                Player onlinePlayer = Bukkit.getPlayerExact(strings[1]);
                if (onlinePlayer == null){
                    Component parsed = mm.deserialize(LangFile.CommandsMessages.Give.NoPlayer);
                    player.sendMessage(parsed);
                    return true;
                } else {
                    onlinePlayer.getInventory().addItem(getDebugStickItem());
                    Component parsed = mm.deserialize(LangFile.CommandsMessages.Give.Success.replace("%player%", player.getName()));
                    player.sendMessage(parsed);
                    if (DebugStickItemCheck.checkPlayer(onlinePlayer)) {
                        PlayerDataManager.addPlayerToDisplayList(onlinePlayer.getUniqueId());
                    }
                    return true;
                }
            }
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
        if (strings.length == 1){
            if (player.hasPermission("debugstickpro.help")){
                list.add("help");
            }
            if (player.hasPermission("debugstickpro.reload")){
                list.add("reload");
            }
            if (player.hasPermission("debugstickpro.give")){
                list.add("give");
            }
            return list;
        }

        if (strings.length == 2){
            if (strings[0].equalsIgnoreCase("give")){
                return null;
            }
        }
        return list;
    }

    private static ItemStack getDebugStickItem(){
        ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);

        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("Debug Stick Pro");
        itemMeta.getPersistentDataContainer().set(PersistentKeys.DEBUG_STICK_ITEM, PersistentDataType.STRING, "debugstickpro");

        if (ConfigFile.CustomModelData.Enabled) {
            itemMeta.setCustomModelData(ConfigFile.CustomModelData.CustomModelData);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
