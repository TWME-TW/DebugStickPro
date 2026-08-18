package dev.twme.debugstickpro.display;

import dev.twme.debugstickpro.DebugStickPro;
import dev.twme.debugstickpro.mode.classic.ClassicActionBarDisplay;
import dev.twme.debugstickpro.mode.copy.CopyActionBarDisplay;
import dev.twme.debugstickpro.mode.freeze.FreezeActionBarDisplay;
import dev.twme.debugstickpro.playerdata.PlayerData;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import dev.twme.debugstickpro.scheduler.PlatformScheduler;
import dev.twme.debugstickpro.utils.DebugStickItem;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ActionBarDisplayTask implements Runnable {
    private final DebugStickPro plugin;

    public ActionBarDisplayTask(DebugStickPro plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (UUID uuid : PlayerDataManager.getDisplaySetSnapshot()) {
            Player player = Bukkit.getPlayer(uuid);

            if (player == null) {
                PlayerDataManager.removePlayerFromDisplayList(uuid);
                continue;
            }

            PlatformScheduler.runForPlayer(plugin, player, () -> displayForPlayer(player));
        }
    }

    private void displayForPlayer(Player player) {
        UUID uuid = player.getUniqueId();
        if (!player.hasPermission("debugstickpro.use")) {
            PlayerDataManager.removePlayerFromDisplayList(uuid);
            return;
        }

        if (!DebugStickItem.checkPlayer(player)) {
            PlayerDataManager.removePlayerFromDisplayList(uuid);
            return;
        }

        PlayerData playerData = PlayerDataManager.getOrCreatePlayerData(uuid);

        switch (playerData.getDebugStickMode()) {
            case CLASSIC:
                Block block = player.getTargetBlockExact(5);
                ActionbarUtil.sendActionBar(player, ClassicActionBarDisplay.getDisplay(uuid, block == null ? null : block.getBlockData()));
                break;
            case COPY:
                ActionbarUtil.sendActionBar(player, CopyActionBarDisplay.getDisplay(uuid));
                break;
            case FREEZE:
                ActionbarUtil.sendActionBar(player, FreezeActionBarDisplay.getDisplay(uuid));
                break;
        }
    }
}
