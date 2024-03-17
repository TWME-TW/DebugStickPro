package dev.twme.debugstickpro.commands;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.configs.LangFile;
import dev.twme.debugstickpro.events.PlayerChangeDebugStickModeEvent;
import dev.twme.debugstickpro.playerdata.DebugStickMode;
import dev.twme.debugstickpro.playerdata.PlayerData;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import dev.twme.debugstickpro.util.DebugStickItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
            DebugStickPro.getInstance().onReload();
            Component parsed = mm.deserialize(LangFile.CommandsMessages.Reload.Success);
            player.sendMessage(parsed);
            return true;
        }
        if (strings[0].equalsIgnoreCase("give")){
            if (strings.length == 1) {
                player.getInventory().addItem(DebugStickItem.getDebugStickItem());

                Component parsed = mm.deserialize(LangFile.CommandsMessages.Give.Success.replace("%player%", player.getName()));
                player.sendMessage(parsed);
                if (DebugStickItem.checkPlayer(player)) {
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
                    onlinePlayer.getInventory().addItem(DebugStickItem.getDebugStickItem());
                    Component parsed = mm.deserialize(LangFile.CommandsMessages.Give.Success.replace("%player%", player.getName()));
                    player.sendMessage(parsed);
                    if (DebugStickItem.checkPlayer(onlinePlayer)) {
                        PlayerDataManager.addPlayerToDisplayList(onlinePlayer.getUniqueId());
                    }
                    return true;
                }
            }
        }

        if (strings[0].equalsIgnoreCase("mode")) {
            if (!player.hasPermission("debugstickpro.mode")) {
                Component parsed = mm.deserialize(LangFile.CommandsMessages.NoPermission);
                player.sendMessage(parsed);
                return true;
            }
            if (strings.length == 1) {
                Component parsed = mm.deserialize(LangFile.CommandsMessages.Mode.Usage);
                player.sendMessage(parsed);
                return true;
            } else {
                if (strings[1].equalsIgnoreCase("classic")) {
                    PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());

                    if (modeChangeEventCancelled(player.getUniqueId(), playerData.getDebugStickMode(), DebugStickMode.Classic)) {
                        return true;
                    }

                    PlayerDataManager.setPlayerData(player.getUniqueId(),playerData.setDebugStickMode(DebugStickMode.Classic));
                    Component parsed = mm.deserialize(LangFile.CommandsMessages.Mode.SuccessSetToClassic);
                    player.sendMessage(parsed);
                    return true;
                }
                if (strings[1].equalsIgnoreCase("copy")) {
                    if (player.hasPermission("debugstickpro.mode.copy")) {
                        PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());

                        if (modeChangeEventCancelled(player.getUniqueId(), playerData.getDebugStickMode(), DebugStickMode.Copy)) {
                            return true;
                        }

                        PlayerDataManager.setPlayerData(player.getUniqueId(),playerData.setDebugStickMode(DebugStickMode.Copy));
                        Component parsed = mm.deserialize(LangFile.CommandsMessages.Mode.SuccessSetToCopy);
                        player.sendMessage(parsed);
                        return true;
                    } else {
                        Component parsed = mm.deserialize(LangFile.CommandsMessages.NoPermission);
                        player.sendMessage(parsed);
                        return true;
                    }
                }
                if (strings[1].equalsIgnoreCase("freeze")) {
                    if (player.hasPermission("debugstickpro.mode.freeze")) {
                        PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());

                        if (modeChangeEventCancelled(player.getUniqueId(), playerData.getDebugStickMode(), DebugStickMode.Freeze)) {
                            return true;
                        }

                        PlayerDataManager.setPlayerData(player.getUniqueId(),PlayerDataManager.getPlayerData(player.getUniqueId()).setDebugStickMode(DebugStickMode.Freeze));
                        Component parsed = mm.deserialize(LangFile.CommandsMessages.Mode.SuccessSetToFreeze);
                        player.sendMessage(parsed);
                        return true;
                    } else {
                        Component parsed = mm.deserialize(LangFile.CommandsMessages.NoPermission);
                        player.sendMessage(parsed);
                        return true;
                    }
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
            if (player.hasPermission("debugstickpro.mode")){
                list.add("mode");
            }
            return list;
        }

        if (strings.length == 2){
            if (strings[0].equalsIgnoreCase("give")){
                return null;
            }

            if (strings[0].equalsIgnoreCase("mode")){
                if (player.hasPermission("debugstickpro.mode")){
                    list.add("classic");
                }
                if (player.hasPermission("debugstickpro.mode.copy")){
                    list.add("copy");
                }
                if (player.hasPermission("debugstickpro.mode.freeze")){
                    list.add("freeze");
                }
                return list;
            }
        }
        return list;
    }

    public static boolean modeChangeEventCancelled(UUID playerUUID, DebugStickMode previousMode, DebugStickMode newMode) {
        PlayerChangeDebugStickModeEvent event = new PlayerChangeDebugStickModeEvent(playerUUID, previousMode, newMode);

        Bukkit.getPluginManager().callEvent(event);

        return event.isCancelled();
    }
}
