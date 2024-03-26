package dev.twme.debugstickpro.display;

import dev.twme.debugstickpro.mode.classic.ClassicActionBarDisplay;
import dev.twme.debugstickpro.mode.copy.CopyActionBarDisplay;
import dev.twme.debugstickpro.mode.freeze.FreezeActionBarDisplay;
import dev.twme.debugstickpro.playerdata.PlayerData;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ActionBarDisplayTask implements Runnable {
    @Override
    public void run() {
        for (UUID uuid : PlayerDataManager.getDisplaySet()) {
            Player player = Bukkit.getPlayer(uuid);

            Block block;

            // player is offline or something
            if (player == null) {
                continue;
            }
            block = player.getTargetBlockExact(5);

            if (!player.hasPermission("debugstickpro.use")) {
                PlayerDataManager.removePlayerFromDisplayList(uuid);
                continue;
            }

            PlayerData playerData = PlayerDataManager.getPlayerData(uuid);

            switch (playerData.getDebugStickMode()) {
                case CLASSIC:
                    if (block == null) {
                        ActionbarUtil.removeActionBar(uuid);
                        continue;
                    }
                    ActionbarUtil.sendActionBar(player, ClassicActionBarDisplay.getDisplay(uuid, block.getBlockData()));
                    continue;
                case COPY:
                    ActionbarUtil.sendActionBar(player, CopyActionBarDisplay.getDisplay(uuid));
                    continue;
                case FREEZE:
                    ActionbarUtil.sendActionBar(player, FreezeActionBarDisplay.getDisplay(uuid));
                    continue;
            }
        }
    }
}
