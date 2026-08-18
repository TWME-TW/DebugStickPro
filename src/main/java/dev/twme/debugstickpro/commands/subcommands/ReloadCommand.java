package dev.twme.debugstickpro.commands.subcommands;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.localization.I18n;
import dev.twme.debugstickpro.localization.Lang;
import dev.twme.debugstickpro.utils.TextUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ReloadCommand {
    public static boolean onReloadCommand(Player player, String[] args) {
        MiniMessage mm = MiniMessage.miniMessage();
        UUID playerUUID = player.getUniqueId();
        if (args.length > 1) {
            return false;
        }
        if (!player.hasPermission("debugstickpro.reload")) {
            Component parsed = mm.deserialize(I18n.string(playerUUID, Lang.CommandsMessages.NoPermission));
            TextUtil.send(player, parsed);
            return true;
        }
        DebugStickPro.getInstance().onReload();
        Component parsed = mm.deserialize(I18n.string(playerUUID, Lang.CommandsMessages.Reload.Success));
        TextUtil.send(player, parsed);
        return true;
    }
}
