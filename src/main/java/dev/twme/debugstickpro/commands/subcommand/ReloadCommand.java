package dev.twme.debugstickpro.commands.subcommand;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.configs.LangFile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

public class ReloadCommand {
    public static boolean onReloadCommand(Player player, String[] args) {
        MiniMessage mm = MiniMessage.miniMessage();
        if (args.length > 1) {
            return false;
        }
        if (!player.hasPermission("debugstickpro.reload")) {
            Component parsed = mm.deserialize(LangFile.CommandsMessages.NoPermission);
            player.sendMessage(parsed);
            return true;
        }
        DebugStickPro.getInstance().onReload();
        Component parsed = mm.deserialize(LangFile.CommandsMessages.Reload.Success);
        player.sendMessage(parsed);
        return true;
    }
}
