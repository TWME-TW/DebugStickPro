package dev.twme.debugstickpro.commands.subcommand;

import dev.twme.debugstickpro.localization.I18n;
import dev.twme.debugstickpro.localization.Lang;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

import java.util.UUID;

public class HelpCommand {
    public static boolean onHelpCommand(Player player, String[] args) {
        MiniMessage mm = MiniMessage.miniMessage();
        UUID playerUUID = player.getUniqueId();
        if (args.length > 1) {
            return false;
        }
        if (!player.hasPermission("debugstickpro.help")) {
            Component parsed = mm.deserialize(I18n.str(playerUUID, Lang.CommandsMessages.NoPermission));
            player.sendMessage(parsed);
            return true;
        }
        for (String message : I18n.list(playerUUID, Lang.CommandsMessages.Help.HelpMessage)) {
            player.sendMessage(mm.deserialize(message));
        }
        return true;
    }
}
